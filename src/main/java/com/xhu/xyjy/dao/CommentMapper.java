package com.xhu.xyjy.dao;


import com.xhu.xyjy.dto.CommentUser;
import com.xhu.xyjy.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

// 添加评论
    @Insert("INSERT INTO Comment (moment_id,comment_id,user_id,user2_id,content,level,creat_time,like_count,status)  VALUES (#{moment_id},#{comment_id},#{user_id},#{user2_id},#{content},#{level},#{creat_time},#{like_count},#{status}) ")
    boolean addComment(Comment comment);

    @Select("select comment.*,user.user_name,user_picture from comment inner join user  on comment.user_id=user.user_id and moment_id= #{moment_id} and level=1")
    List<CommentUser> findComment1(Integer moment_id);

    @Select("SELECT c.*,u1.user_name,u1.user_picture,u2.user_name as user2_name,u2.user_picture as user2_picture from comment c INNER JOIN user as u1 ON c.user_id = u1.user_id INNER JOIN user as u2 on c.user2_id=u2.user_id and comment_id=#{comment_id} and status=1 and level=2")
    List<CommentUser> findComment2(Integer comment_id);

    @Update("update comment set like_count =like_count+1 where id =#{id}")
    boolean addLikeCount(int id);
}
