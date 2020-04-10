package com.xhu.xyjy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.FriendMapper;
import com.xhu.xyjy.dto.FriendInfo;
import com.xhu.xyjy.pojo.Friend;
import com.xhu.xyjy.pojo.User;
import org.hibernate.validator.constraints.EAN;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
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


    @Override
    public List<User> getAdviceF(int user_id) {
//       int max=friendMapper.selectMax();
       List<Friend> list=friendMapper.selectUnkonwn(user_id);//得到不认识的人ID
//      int n=list.size();
       List<Integer> a=new ArrayList<>();
        Iterator it2 = list.iterator();
        while( it2.hasNext()){
            int b=((Friend)it2.next()).getUser_id();
            List<Friend> l=friendMapper.selectUnion(user_id,b);
            if(l.size()>=1){
                a.add(b);
            }
        }

        String s="";
        Iterator it1 = a.iterator();
        while (it1.hasNext()){
            s=s+it1.next()+',';
        }
        s=s.substring(0,s.length()-1);
        List<User> users=friendMapper.selectFriends(s);
        return  users;
    }


}
