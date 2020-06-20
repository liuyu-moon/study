package com.xhu.xyjy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.CommentMapper;
import com.xhu.xyjy.dao.MomentMapper;
import com.xhu.xyjy.dto.CommentUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Comment;
import com.xhu.xyjy.pojo.Inform;
import com.xhu.xyjy.pojo.Moment;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    MomentMapper momentMapper;

    @Override
    public PageInfo<CommentUser> selectComment1(int moment_id, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<CommentUser> comments = commentMapper.findComment1(moment_id);
        return new PageInfo<>(comments);
    }

    @Override
    public PageInfo<CommentUser> selectComment2(int id, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<CommentUser> comments = commentMapper.findComment2(id);
        for (CommentUser s : comments) {
            System.out.println(s.getId()+"  "+s.getUser_name()+"  "+s.getUser2_name());
}
        return new PageInfo<>(comments);
    }

    @Override
    public ResultData addComment(int moment_id,int user2_id, String content,int user1_id) {
        Comment comment= new Comment();
//        数据填充
        comment.setMoment_id(moment_id);
        comment.setComment_id(0);//一级评论,父评论ID为0
        comment.setUser_id(user1_id);
        comment.setUser2_id(0);
        comment.setContent(content);
        comment.setLevel(1);//一级评论
        comment.setCreat_time(new Timestamp(System.currentTimeMillis()));
        comment.setLike_count(0);
        comment.setStatus(1);
        System.out.println("iml"+moment_id);

        Inform inform =new Inform();
        inform.setMoment_id(moment_id);
        inform.setUser1_id(user1_id);
        inform.setUser2_id(user2_id);
        inform.setContent("评论了你的动态："+content);
        Calendar calendar=Calendar.getInstance();
        inform.setTime(calendar.getTime());
        //type1 为动态评论
        inform.setType(2);

        if(commentMapper.addComment(comment)&& momentMapper.addCommentCount(moment_id))
        {
            momentMapper.addInform(inform);
            return  new ResultData(200,"发表评论成功");
        }
        return  new ResultData(200,"发表评论成功");

    }
//添加二级评论
    @Override
    public ResultData addComment2(Comment comment) {
//        数据填充
        comment.setLevel(2);//二级评论
        comment.setCreat_time(new Timestamp(System.currentTimeMillis()));
        comment.setLike_count(0);
        comment.setStatus(1);
        comment.getMoment_id();

//数据填充
        Inform inform =new Inform();
        inform.setMoment_id(comment.getMoment_id());
        inform.setUser1_id(comment.getUser_id());
        inform.setUser2_id(comment.getUser2_id());
        inform.setContent("评论了你的留言："+comment.getContent());
        Calendar calendar=Calendar.getInstance();
        inform.setTime(calendar.getTime());
        inform.setType(3);

       if( commentMapper.addComment(comment)&& momentMapper.addCommentCount(comment.getMoment_id())){
           momentMapper.addInform(inform);
        return  new ResultData(200,"评论成功")  ;
       }
        return new ResultData(9005,"评论失败");
    }

    @Override
    public ResultData addLikeCount(int id) {
        if( commentMapper.addLikeCount(id)){
            return  new ResultData(200,"点赞成功");
        }
        return  new ResultData(9004,"点赞失败");
    }
}
