package com.xhu.xyjy.dao;

import com.xhu.xyjy.dto.FriendInfo;
import com.xhu.xyjy.pojo.Friend;
import com.xhu.xyjy.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface  FriendMapper {

    //查询好友
    @Select("select user.* ,friendship.group_id ,group_name ,add_time FROM user inner join friendship on user.user_id=friendship.friend_id and friendship.user_id=#{user_id} and status =1")
    List<FriendInfo> selectFriend (int user_id);

//    查询用户所有好友
//    @Select("Select friend_id,group_id,group_name, add_time from friendship where user_id=#{user_id}")
//    List<Friend> selectFriendByID();

    //按名称搜索用户好友，模糊查询
    @Select("select user.* ,friendship.group_id ,group_name ,add_time FROM user inner join friendship on user.user_id=friendship.friend_id and friendship.user_id=#{user_id} and status =1 and user_name like CONCAT('%', #{user_name}, '%')")
    List<FriendInfo> selectFriendByName(int user_id,String user_name);


    @Select("SELECT user_id, friend_id from  friendship ORDER BY user_id")
    List<Friend> selectUF();

    @Select("SELECT MAX(user_id)  FROM friendship")
    int selectMax();

    @Select("SELECT  DISTINCT  user_id FROM friendship where  user_id not in (SELECT DISTINCT  friend_id FROM friendship WHERE user_id= #{user_id}) and user_id!=#{user_id}")
    List<Friend> selectUnkonwn(int user_id);

    @Select("select  friend_id from friendship WHERE user_id=#{a} AND\n" +
            "\n" +
            "friend_id in(\n" +
            "\n" +
            "select friend_id from friendship WHERE  user_id=#{b})\n")
    List<Friend> selectUnion(int a,int b);

    @Select("select * from user where user_id in(${ids}) ")
    List<User> selectFriends(@Param("ids") String ids);

    //判断是否为好友
    @Select("select count(*) from friendship where user_id=#{user_id} and friend_id=#{friend_id}")
    int  isFriend(int user_id, int friend_id);


    //添加好友
    @Insert("insert into friendship (user_id,friend_id,add_time,group_id) values (#{user_id},#{friend_id},#{add_time},#{type})")
    boolean addFriend(int user_id, int friend_id,int type, Timestamp add_time);



    @Select("SELECT * FROM `user` WHERE user_id in\n" +
            "\n" +
            "(SELECT friend_id FROM friendship WHERE user_id=#{user_id} and group_id=#{type})")
    List<User> findGroupFriend(int user_id, int type);
}

