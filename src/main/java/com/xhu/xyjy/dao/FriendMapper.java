package com.xhu.xyjy.dao;

import com.xhu.xyjy.dto.FriendInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
    @Select("select user.* ,friendship.group_id ,group_name ,add_time FROM user inner join friendship on user.user_id=friendship.friend_id and friendship.user_id=#{user_id} and status =1 and user_name like CONCAT(CONCAT('%', #{user_name}), '%')")
    List<FriendInfo> selectFriendByName(int user_id,String user_name);
}

