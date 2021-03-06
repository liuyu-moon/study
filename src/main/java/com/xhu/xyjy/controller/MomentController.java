package com.xhu.xyjy.controller;


import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.dto.RecordUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Moment;
import com.xhu.xyjy.pojo.Record;
import com.xhu.xyjy.pojo.User;
import com.xhu.xyjy.service.FriendService;
import com.xhu.xyjy.service.MomentService;
import com.xhu.xyjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MomentController {

    @Autowired
    MomentService momentService;
    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;

    @RequestMapping("/gopublish")
    public String goPublish(){
        return "publish";
    }



    @RequestMapping("/publish")
    @ResponseBody
    public ResultData Publish(Moment moment, MultipartFile[] multipartFile,MultipartFile[] multipartFile2,HttpServletRequest request)

    {
        HttpSession session= request.getSession();
        String s=session.getAttribute("userId").toString();
        int userid= Integer.parseInt(s);
        moment.setUser_id(userid);
        return  momentService.publish(moment,multipartFile,multipartFile2);
    }

    @RequestMapping("/searchMoment/{action}")
    public  String searchMomentList(@PathVariable(name = "action") String action, Model model, @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    HttpServletRequest request){
        PageInfo<MomentUser> pageInfo=momentService.findMomentByTag(action,page,pageSize);
        model.addAttribute("moments",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        return  "index";

    }

    @RequestMapping("/deletemoment")
    @ResponseBody
    public  ResultData deletemoment( int id){
        if(id==0){
            return  new  ResultData(9002,"动态删除失败");
        }
        return  momentService.deletemoment(id);
    }


    @RequestMapping("/goindex")
    public String MomentList(Model model,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "3") Integer pageSize,
                             HttpServletRequest request){

        HttpSession session= request.getSession();
        String s=session.getAttribute("userId").toString();
        int userid= Integer.parseInt(s);
        //可能认识的人
        List<User> a=friendService.getAdviceF(userid);
        //附近的人
        List<User> b=userService.findNearby(userid);

        //感兴趣的人

        List<User> f=userService.findInterest(userid);

        //生日好友
        List<User> c=userService.findBirthday(userid);

        //我最近浏览的人
        List<RecordUser> d=userService.findRecentA(userid);
        //最近访问我的人
        List<RecordUser> e=userService.findRecentB(userid);



        int like_count=userService.findLikeCount(userid);
        int view_count=userService.findViewCount(userid);

        model.addAttribute("advice",a);
        model.addAttribute("nearby",b);
        model.addAttribute("birthday",c);
        model.addAttribute("ISee",d);
        model.addAttribute("SeeI",e);
        model.addAttribute("interest",f);
         model.addAttribute("like_count",like_count);
        model.addAttribute("view_count",view_count);

        PageInfo<MomentUser> pageInfo=momentService.selectAll(page,pageSize);
        model.addAttribute("moments",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        //查找登陆者信息
        model.addAttribute("loginer",userService.findUserInfo(userid));
        //
        return  "index";
    }






}
