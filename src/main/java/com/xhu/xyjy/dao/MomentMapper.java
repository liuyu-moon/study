package com.xhu.xyjy.dao;

import com.xhu.xyjy.dto.InformUser;
import com.xhu.xyjy.dto.MomentUser;
import com.xhu.xyjy.dto.RecordUser;
import com.xhu.xyjy.pojo.Inform;
import com.xhu.xyjy.pojo.Moment;
import com.xhu.xyjy.pojo.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MomentMapper {


    @Select("select moment.*,user_name,user_picture  from  Moment ,User where  moment.user_id=user.user_id")
    List<MomentUser> selectMoment();


    @Insert("INSERT INTO moment (user_id,description,picture,video,time,tag)  VALUES (#{user_id},#{description},#{picture},#{video},#{time},#{tag})")
    boolean add (Moment moment);

    @Select("select * from moment where id=#{id}")

    Moment findOneById(int id);


    @Select("SELECT moment.*,user.user_name,user_picture from moment  inner join user  on moment.user_id=user.user_id ORDER BY moment.like_count desc ")
    List<MomentUser> findAll();
    //查找自己的动态
    @Select("select moment.*,user.user_name,user_picture from moment  inner join user  on moment.user_id=user.user_id and moment.user_id=#{user_id} ORDER BY moment.like_count desc")
    List<MomentUser> findById(Integer user_id);
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

    @Insert("insert into  inform (user1_id,user2_id,moment_id,content,time,type) values (#{user1_id},#{user2_id},#{moment_id},#{content},#{time},#{type})")
    boolean addInform(Inform inform);



    @Update("update moment set comment_count =comment_count+1 where id =#{id}")
    boolean addCommentCount(int id);

    @Select("SELECT moment.*,user.user_name,user_picture from moment inner join user  on moment.user_id=user.user_id and tag like  CONCAT('%',#{tag},'%') order by time")
    List<MomentUser> findbyTag(String tag);

    @Delete("delete from moment where id=#{id}")
    boolean deleteById(int id);

    @Select("select inform.*,u1.user_name as user1_name,u1.user_picture as user1_picture,u2.user_name as user2_name,u2.user_picture as user2_picture from inform \n" +
            "            Inner JOIN user as u1  on inform.user1_id=u1.user_id \n" +
            "            Inner JOIN user as u2 on inform.user2_id=u2.user_id \n" +
            "            and inform.user2_id=#{user_id}")
    List<InformUser> findFocus(int user_id);


}
