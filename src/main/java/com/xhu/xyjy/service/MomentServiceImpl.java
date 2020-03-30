package com.xhu.xyjy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.MomentMapper;
import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Moment;
import com.xhu.xyjy.utils.StringUtil;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.List;

import static com.xhu.xyjy.utils.uploadUtil.upload;


@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    MomentMapper momentMapper;


    //发布动态

    @Override
    public ResultData publish(Moment moment,MultipartFile[] file) {
        if (file==null) {
            System.out.println("ceshi5555");
            return new ResultData(9001, "文件数据异常");
        }
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
        resultData=upload(file,".jpg.gif.png");
        if(resultData.getCode()!=200)
        {
           return  new ResultData(401,"图片上传失败");
        }
        System.out.println("RESUlt"+resultData.toString());
        moment.setPicture(resultData.getData().toString());
        moment.setTime(new Timestamp(System.currentTimeMillis()));
        System.out.println("加入数据库前"+moment.toString());

        if (momentMapper.add(moment)) {
            System.out.println("44444444");
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
    public PageInfo<Moment> selectById ( Integer user_id,Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Moment> moments = momentMapper.findById(user_id);
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
    public ResultData addLikeCount(Integer id) {
       if( momentMapper.addLikeCount(id)){
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
