package com.xhu.xyjy.service;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.FriendInfo;
import com.xhu.xyjy.pojo.User;


public interface FriendService {
    PageInfo<FriendInfo> findFriendList(int user_id,Integer page,Integer pageSize);
    PageInfo<FriendInfo> findFriendByName(int user_id,String user_name,Integer page,Integer pageSize);

}