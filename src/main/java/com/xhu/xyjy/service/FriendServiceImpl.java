package com.xhu.xyjy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.FriendMapper;
import com.xhu.xyjy.dto.FriendInfo;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.Friend;
import com.xhu.xyjy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
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
        //a用来存放推荐好友ID
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
        if(s.length()>1){
            s=s.substring(0,s.length()-1);
        }
        else {
            s="null";
        }

        List<User> users=friendMapper.selectFriends(s);
        return  users;
    }

    @Override
    public int isFreind(int user_id, int friend_id) {
         return  friendMapper.isFriend(user_id,friend_id);
    }

    @Override
    public ResultData addFriend(int user_id, int friend_id,int type) {

       if(friendMapper.addFriend(user_id,friend_id,type,
               new Timestamp(System.currentTimeMillis()))
       &&friendMapper.addFriend(friend_id,user_id,type,
               new Timestamp(System.currentTimeMillis()))
       ){
           return  new ResultData(200,"添加好友成功");
       }
       else return new ResultData(2001,"添加好友失败");
    }

    @Override
    public List<User> groupFriend(int user_id,int type) {

       return friendMapper.findGroupFriend(user_id,type);

    }


}
