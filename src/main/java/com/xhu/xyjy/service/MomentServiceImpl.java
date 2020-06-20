package com.xhu.xyjy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.MomentMapper;
import com.xhu.xyjy.dao.UserMapper;
import com.xhu.xyjy.dto.InformUser;
import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Inform;
import com.xhu.xyjy.pojo.Moment;
import com.xhu.xyjy.pojo.Record;
import com.xhu.xyjy.utils.StringUtil;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.xhu.xyjy.utils.uploadUtil.upload;

/**
 *
 */

@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    MomentMapper momentMapper;

    @Autowired
    UserMapper userMapper;


    //发布动态

    @Override
    public ResultData publish(Moment moment,MultipartFile[] file,MultipartFile[] file2) {

        System.out.println("S层进来");
        System.out.println("1111111"+moment.toString());
        if (moment == null) {

            return new ResultData(5001, "发布动态发生异常！");
        }
        //验证数据
//        if (moment.getUser_id() == 0) {
//            return new ResultData(4002, "用户ID不能为空");
//        }
        moment.setUser_id(1);


        if (moment.getDescription() == null || moment.getDescription().isEmpty()) {
            System.out.println("333333");
            return new ResultData(4003, "发布内容不能为空");
        }
        ResultData resultData =new ResultData();
        ResultData resultData2 =new ResultData();
        if(file.length!=0){
            resultData=upload(file,".jpg.jpeg.gif.png","F:\\xyjy\\src\\main\\resources\\static\\image\\moment",1);
            if(null!=resultData&&resultData.getCode()!=200)
            {
                return  new ResultData(401,"图片上传失败");
            }
            else
                moment.setPicture(resultData.getData().toString());
        }
       if(file2.length!=0){
          resultData2=upload(file2,".mp4.webm.ogg","F:\\xyjy\\src\\main\\resources\\static\\video\\moment",2);
           if(null!=resultData2&&resultData2.getCode()!=200)
           {
               return  new ResultData(401,"视频上传失败");
           }
           else
               moment.setVideo(resultData2.getData().toString());
       }


        System.out.println("RESUlt"+resultData.toString());


        moment.setTime(new Timestamp(System.currentTimeMillis()));
        System.out.println("加入数据库前"+moment.toString());

        if (momentMapper.add(moment)) {

            return new ResultData(200, "动态发布成功");
        }
        return new ResultData(401, "动态发布失败");

    }

        //展示热搜
    @Override
    public PageInfo<MomentUser> selectAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<MomentUser> moments = momentMapper.findAll();
        return new PageInfo<>(moments);
    }

//查找用户自己的动态
    @Override
    public PageInfo<MomentUser> selectById ( Integer user_id,Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<MomentUser> moments = momentMapper.findById(user_id);
        return new PageInfo<>(moments);
    }

    @Override
    public PageInfo<MomentUser> selectFriendById(Integer user_id, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<MomentUser> moments = momentMapper.findFriendById(user_id);
        System.out.println("list信息"+moments.toString());
        return new PageInfo<>(moments);
    }


    @Override
    public ResultData selectMomentById(Integer id) {
        MomentUser moment=momentMapper.findMomentById(id);
        if(moment!=null)
        return new ResultData(200,"进入详情页面成功",moment);
        else
            return new ResultData(9006,"进入详情页面失败");
    }

    @Override
    public PageInfo<MomentUser> findMomentByTag(String action,Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<MomentUser> moments=momentMapper.findbyTag(action);
        return new PageInfo<>(moments);

    }

    @Override
    public ResultData deletemoment(int id) {
        if(momentMapper.deleteById(id)){
            return new  ResultData(200,"动态信息删除成功");
        }
        else {
            return new  ResultData(9001,"动态信息删除失败");
        }
    }

    @Override
    public PageInfo<InformUser> selectFocus(int user_id, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<InformUser> informUsers=momentMapper.findFocus(user_id);
        return new PageInfo<>(informUsers);
    }

    @Override
    public ResultData addrecord(int moment_id, int userid) {
       int user2_id=(momentMapper.findOneById(moment_id)).getUser_id();
       if(user2_id== userid){
           return  new ResultData(200,"访问记录更新成功");
       }

      if(null==userMapper.findRecordOne(userid,user2_id)) {
          Record record=new Record();
          record.setUser1_id(userid);
          record.setUser2_id(user2_id);
          record.setMoment_id(moment_id);
          record.setTime(new Date());
          record.setType(2);
          userMapper.insertRecent(record);
      }
      else {
          userMapper.updateRecordTimeandTypeandMoment(userid,user2_id,moment_id,2);
      }
      return  new ResultData(200,"访问记录更新成功");
    }

    @Override
    public ResultData addLikeCount(Integer moment_id,Integer user1_id,Integer user2_id) {
        Inform inform =new Inform();
        inform.setMoment_id(moment_id);
        inform.setUser1_id(user1_id);
        inform.setUser2_id(user2_id);
        inform.setContent("为你的动态点赞");
        Calendar calendar=Calendar.getInstance();
        inform.setTime(calendar.getTime());

        //type1 为动态点赞
        inform.setType(1);
       if( momentMapper.addLikeCount(moment_id)
               &&momentMapper.addInform(inform)
       ){
           return  new ResultData(200,"点赞成功");
       }

        return  new ResultData(9004,"点赞失败");
    }

    @Override
    public ResultData addViewCount(Integer id) {


        if( momentMapper.addViewCount(id)){
            return  new ResultData(200,"浏览成功");
        }

        return  new ResultData(9004,"浏览失败");
    }




}
