package com.xhu.xyjy.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.CommentMapper;
import com.xhu.xyjy.dto.CommentUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Comment;
import com.xhu.xyjy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    //展示二级评论
    @RequestMapping("/showcomment2")
    @ResponseBody
    public ResultData ShowComment(int commentId,@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<CommentUser> pageInfo=commentService.selectComment2(commentId,page,pageSize);

       ResultData resultData=new ResultData();
       if(pageInfo !=null){
           resultData.setMsg("成功");
           resultData.setCode(200);
           resultData.setData(pageInfo);
           resultData.setData2(pageInfo.getList());
           return  resultData;
       }
       resultData.setMsg("失败");
       return  resultData;
    }


    @RequestMapping("/addcomment1")
    @ResponseBody
    protected  ResultData addComment1(int moment_id,int user2_id, String content,HttpServletRequest request){
        System.out.println("4545"+moment_id);
        if(moment_id==0||content==null){
            return  new ResultData(9002,"评论异常");
        }
        HttpSession session=request.getSession();
        String s=session.getAttribute("userId").toString();
        int user_id= Integer.parseInt(s);
        return commentService.addComment(moment_id,user2_id,content,user_id);
    }
    @RequestMapping("/addcomment2")
    @ResponseBody
    protected  ResultData addComment2(Comment comment,HttpServletRequest request){

        if(comment.getComment_id()==0||comment.getContent()==null||comment.getUser2_id()==0){
            return  new ResultData(9002,"评论异常");
        }
        HttpSession session=request.getSession();
        String s=session.getAttribute("userId").toString();
        int user_id= Integer.parseInt(s);
        comment.setUser_id(user_id);
        return commentService.addComment2(comment);
    }

}
