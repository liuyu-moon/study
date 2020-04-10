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

import java.sql.Timestamp;
import java.util.List;

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
//        System.out.println(dbUser.getUser_id()+" "+dbUser.getUser_logintime());
        userMapper.updateLogintime(dbUser);
        if (dbUser.getUser_type()==0){
            return new ResultData(200,"用户登录成功",dbUser,dbUser.getUser_id());
        }
        return new ResultData(200,"管理员登录成功",dbUser,dbUser.getUser_id());
    }
    }



