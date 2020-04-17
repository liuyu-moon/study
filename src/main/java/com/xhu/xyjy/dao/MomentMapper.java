package com.xhu.xyjy.dao;

import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.pojo.Moment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MomentMapper {


    @Insert("INSERT INTO moment (user_id,description,picture,video,time,tag)  VALUES (#{user_id},#{description},#{picture},#{video},#{time},#{tag})")
    boolean add (Moment moment);

    @Select("SELECT moment.*,user.user_name,user_picture from moment  inner join user  on moment.user_id=user.user_id ORDER BY moment.like_count desc ")
    List<MomentUser> findAll();
    //查找自己的动态
    @Select("SELECT * from moment where user_id =#{user_id}")
    List<Moment> findById(Integer user_id);
 //单一动态详细
    @Select("SELECT moment.*,user.user_name,user_picture from moment inner join user  on moment.user_id=user.user_id and id =#{id}")
    MomentUser findMomentById(Integer id);
    //朋友圈
    @Select("SELECT moment.*,user.user_name,user_picture from moment inner join user  on moment.user_id=user.user_id and moment.user_id in (SELECT friend_id FROM friendship where user_id = #{user_id})")
    List<MomentUser> findFriendById(Integer user_id);

    @Update("update moment set view_count =view_count+1 where id =#{id}")
    boolean addViewCount(int id);

    @Update("update moment set like_count =like_count+1 where id =#{id}")
    boolean addLikeCount(int id);

    @Update("update moment set comment_count =comment_count+1 where id =#{id}")
    boolean addCommentCount(int id);

    @Select("SELECT moment.*,user.user_name,user_picture from moment inner join user  on moment.user_id=user.user_id and tag like  CONCAT('%',#{tag},'%') order by time")
    List<MomentUser> findbyTag(String tag);

    @Delete("delect from moment where id=${id}")
    int deleteById(int id);
}
