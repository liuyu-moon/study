package com.xhu.xyjy.service;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.FriendInfo;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.User;

import java.util.List;


public interface FriendService {
    PageInfo<FriendInfo> findFriendList(int user_id,Integer page,Integer pageSize);
    PageInfo<FriendInfo> findFriendByName(int user_id,String user_name,Integer page,Integer pageSize);
   List<User> getAdviceF(int user_id);

    int isFreind(int user_id, int friend_id);

     ResultData addFriend(int user_id, int friend_id,int type);

    List<User> groupFriend(int user_id,int type);
}
