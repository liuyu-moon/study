package com.xhu.xyjy.controller;


import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Moment;
import com.xhu.xyjy.service.MomentService;
import com.xhu.xyjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MomentController {

    @Autowired
    MomentService momentService;
    @Autowired
    UserService userService;

    @RequestMapping("/gopublish")
    public String goPublish(){
        return "publish";
    }

//    @RequestMapping("/index")
//    public String goabc(){
//        return "index";
//    }

    @RequestMapping("/publish")
    @ResponseBody
    public ResultData Publish(Moment moment, MultipartFile[] multipartFile,HttpServletRequest request)

    {

        if(multipartFile!=null){
            System.out.println("c层进去");
        }

        System.out.println(moment.toString());
//        System.out.println(multipartFile.getOriginalFilename());
//        User user=(User) request.getSession().getAttribute("user");
//        moment.setUser_id((user.getUser_id()));
        moment.setUser_id(1);
        System.out.println(multipartFile.length+"length");
        for (int i=0;i<multipartFile.length;i++)
        {
            System.out.println(multipartFile[i].getOriginalFilename());
        }
    return  momentService.publish(moment,multipartFile);
    }


//    @RequestMapping("/goMomentShow")
    @RequestMapping("/goindex")
    public String MomentList(Model model,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             HttpServletRequest request){

        HttpSession session= request.getSession();
        String s=session.getAttribute("userId").toString();
        int userid= Integer.parseInt(s);
        //查找登陆者信息

        PageInfo<MomentUser> pageInfo=momentService.selectAll(page,pageSize);
        model.addAttribute("moments",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("loginer",userService.findUserInfo(userid));
        return  "index";
    }






}