package com.xhu.xyjy.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dao.ChatMapper;
import com.xhu.xyjy.dao.UserMapper;
import com.xhu.xyjy.dto.ChatUser;
import com.xhu.xyjy.dto.MessageUser;
import com.xhu.xyjy.dto.ResultData;
import com.xhu.xyjy.pojo.ChatList;
import com.xhu.xyjy.pojo.Message;
import com.xhu.xyjy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.xhu.xyjy.websocket.GroupWebSocket.groupWebSocket;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatMapper chatMapper;

    @Autowired
    UserMapper userMapper;
    @Override
    public int findChat(int user_id, int user2_id) {

      ChatList chat=chatMapper.selectChatList(user_id,user2_id);

      //如果chatlist表里面没有，就插入
      if (chat==null) {
          ChatList chatList=new ChatList();
          //数据填充
          chatList.setUser_id(user_id);
          chatList.setUser2_id(user2_id);
          chatList.setLast_time(new Timestamp(System.currentTimeMillis()));
          //插入一条新的chat信息
         chatMapper.addChat(chatList);
         //A对B有一条List,B对A就有一条
         chatList.setUser_id(user2_id);
         chatList.setUser2_id(user_id);
          chatMapper.addChat(chatList);

          return 1;
      }
      else {
          return 0;
      }
    }

    @Override
    public PageInfo<ChatUser> findChatList(int userid,Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ChatUser> chatLists=chatMapper.selectChatListInfo(userid);
        return new PageInfo<>(chatLists);
    }

    @Override public PageInfo<MessageUser> findHistoryMsg(int userid, int user2_id, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<MessageUser> messageList=chatMapper.selectHistoryMessage(userid,user2_id);
        //将未读消息数清空
        chatMapper.clearUnread(userid,user2_id);
        return new PageInfo<>(messageList);
    }

    @Override
    public void addMessage(Message message1) {
        chatMapper.addMessage(message1);
    }
    //添加未读消息
    @Override
    public int addUnread(int user_id,int user2_id) {
        chatMapper.addUnread(user_id,user2_id);
        return 0;
    }

    @Override
    public void updateTime(int user_id, int user2_id) {
        Timestamp time= new Timestamp(System.currentTimeMillis());
        chatMapper.updateLastTime(user_id,user2_id,time);
        chatMapper.updateLastTime(user2_id,user_id,time);
    }

    @Override
    public List<User> findUsers() {

        String s="1,";
        for (String value : groupWebSocket.keySet()) {

            System.out.println("Value = " + value);
            s+=value+',';

        }
        System.out.println(s.length());
        s=s.substring(0,s.length()-1);

        List<User> users=userMapper.findUsers(s);


        return users;
    }
}
