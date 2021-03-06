package com.xhu.xyjy.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.CommentUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Comment;
import com.xhu.xyjy.service.CommentService;
import com.xhu.xyjy.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DetailController {
    @Autowired
    MomentService momentService;
    @Autowired
    CommentService commentService;

//    详情页面 。展示该动态和该动态下所有的一级评论
    @RequestMapping("/godetail/{id}")
    public String goDetail(@PathVariable(name = "id") int id,  Model model, @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,HttpServletRequest request){
        System.out.println("进入detail 层");
        momentService.addViewCount(id);
        HttpSession session= request.getSession();
        String s=session.getAttribute("userId").toString();
        int userid= Integer.parseInt(s);
        momentService.addrecord(id,userid);
        ResultData resultData=  momentService.selectMomentById(id);
        System.out.println(resultData.toString());
        PageInfo<CommentUser> pageInfo=commentService.selectComment1(id,page,pageSize);

      if (resultData.getCode()==200&&pageInfo!=null){
          model.addAttribute("moment",resultData.getData());
          model.addAttribute("comments",pageInfo.getList());
          model.addAttribute("pageInfo",pageInfo);
      }
        model.addAttribute("msg",resultData.getMsg());
        return "detail";
    }

    @RequestMapping("/addlikemoment")
    @ResponseBody
    public  ResultData giveLikeMoment(int  moment_id,int user1_id,int user2_id){
        System.out.println("点赞");
       ResultData resultData= momentService.addLikeCount(moment_id,user1_id,user2_id);
        System.out.println("点赞2");
        return resultData;
    }

    @RequestMapping("/addlikecomment")
    @ResponseBody
    public  ResultData giveLikeComment(int  id){
        System.out.println("点赞");
        ResultData resultData= commentService.addLikeCount(id);
        System.out.println("点赞2");
        return resultData;
    }
}
