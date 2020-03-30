package com.xhu.xyjy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.FriendMapper;
import com.xhu.xyjy.dto.FriendInfo;
import com.xhu.xyjy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;
    @Override
    public PageInfo<FriendInfo> findFriendList(int user_id, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List <FriendInfo>  friendList=friendMapper.selectFriend(user_id);
        return new PageInfo<>(friendList );

    }

    @Override
    public PageInfo<FriendInfo> findFriendByName(int user_id, String user_name, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List <FriendInfo>  friendList=friendMapper.selectFriendByName(user_id,user_name);
        return new PageInfo<>(friendList );
    }

}
