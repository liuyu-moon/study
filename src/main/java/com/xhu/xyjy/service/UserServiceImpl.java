package com.xhu.xyjy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.UserMapper;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Admin;
import com.xhu.xyjy.pojo.User;
import com.xhu.xyjy.utils.MD5Util;
import com.xhu.xyjy.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

import static com.xhu.xyjy.utils.uploadUtil.upload;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 用户列表
     * @return
     */
    @Override
    public List<User> listAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findUserInfo(int user_id) {
            User user=userMapper.findById(user_id);
            return  user;
    }

    @Override
    public int findUnread(int userId) {

        return  userMapper.findUnread(userId);
    }

    @Override
    public List<User> findNearby(int userid) {
        //范围半径，默认10000米
        int raidus=1000000;
        User user=userMapper.findById(userid);
        double longitude=user.getLng();
        double latitude=user.getLat();

        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;
        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;

         return  userMapper.selectNearby(maxLat,minLat,maxLng,minLng,userid);


    }

    @Override
    public ResultData update(User user) {
        if(userMapper.updateInfo(user)){
            return  new ResultData(200,"用户资料更新成功");
        }
        else {
           return  new ResultData(9001,"用户资料更新失败");
        }
    }

    @Override
    public ResultData updatepic(int user_id, MultipartFile file[]) {

        if(user_id==0||file==null){
            return new ResultData(9001,"请重新上传头像");
        }
        ResultData resultData=new ResultData();
        resultData=upload(file,".jpg.jpeg.gif.png","F:\\xyjy\\src\\main\\resources\\static\\image\\pic",1);
        User user=new User();
        user.setUser_id(user_id);
        user.setUser_picture( resultData.getData().toString());
        if(userMapper.updatePic(user)){
            return new ResultData(200,"修改头像成功");
        }
        else {
            return new ResultData(9001,"修改头像失败");
        }

    }


    //注册
    @Override
    public ResultData saveUser(User user) {

        System.out.println("service层");
        if (user==null){
            return new ResultData(5001,"用户注册异常，请稍后再试！");
        }
        //验证数据
        if (user.getUser_name()==null  || user.getUser_name().isEmpty()){
            return new ResultData(4002,"用户名不能为空");
        }
        if (user.getUser_pwd()==null || user.getUser_pwd().isEmpty()){
            return new ResultData(4003,"登录密码不能为空");
        }
        if (user.getUser_phone()==null  ||  user.getUser_phone().isEmpty()){
            return new ResultData(4004,"手机号不能为空");
        }
        //数据填充
        user.setUser_picture("image\\moment\\2020/02/13\\016fb1a16c9d4a5d90c885c5e2ce46f8.jpg");
        user.setUser_type(1);
        user.setUser_status(1);
        user.setUser_addtime(new Timestamp(System.currentTimeMillis()));
        user.setUser_logintime(null);
        user.setUser_school(null);
        user.setUser_sex("未设置");
        user.setUser_pwd(MD5Util.MD55(user.getUser_pwd()));
        System.out.println(user.toString());
        try {

            if (userMapper.saveUser(user)) {

                return new ResultData(200,"添加用户成功");

            }
        }catch (Exception e){
            System.out.println("666677777");
            return new ResultData(5003,"用户名已存在！");
        }

        return new ResultData(5002,"添加用户失败！");
    }

    //登陆
    @Override
    public ResultData findUserById(User user) {
        System.out.println(user.toString()+"sss");

        if(user==null){
            return new ResultData(4001,"登录数据异常");
        }
        if (user.getUser_name()==null  || user.getUser_name().isEmpty()){
            return new ResultData(4002,"用户名不能为空");
        }
        if (user.getUser_pwd()==null || user.getUser_pwd().isEmpty()){
            return new ResultData(4003,"登录密码不能为空");
        }
        //查询数据库
        User dbUser = userMapper.selectUserByName(user.getUser_name());
        if (dbUser==null){
            return new ResultData(4004,"该用户不存在");
        }
        if (dbUser.getUser_status()==0){
            return new ResultData(4006,"账户已被停用！");
        }
        //验证密码
        if (!dbUser.getUser_pwd().equals(MD5Util.MD55(user.getUser_pwd()))){
            return new ResultData(4005,"密码不正确");
        }

        //注入登录时间
        dbUser.setUser_logintime(new Timestamp(System.currentTimeMillis()));

        //注入登录地点
        dbUser.setLng(user.getLng());
        dbUser.setLat(user.getLat());
//        System.out.println(dbUser.getUser_id()+" "+dbUser.getUser_logintime());
        userMapper.updateLogintimeAndPosition(dbUser);
        if (dbUser.getUser_type()==1){
            return new ResultData(200,"用户登录成功",dbUser,dbUser.getUser_id());
        }
        return new ResultData(200,"管理员登录成功",dbUser,dbUser.getUser_id());
    }
    }



