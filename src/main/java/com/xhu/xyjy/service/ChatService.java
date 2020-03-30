package com.xhu.xyjy.service;


import com.github.pagehelper.PageInfo;
import com.xhu.xyjy.dto.ChatUser;
import com.xhu.xyjy.dto.MessageUser;
import com.xhu.xyjy.pojo.ChatList;
import com.xhu.xyjy.pojo.Message;

public  interface ChatService {

    int findChat(int user_id, int user2_id);

    PageInfo<ChatUser> findChatList(int userid, Integer page, Integer pageSize);
    PageInfo<MessageUser> findHistoryMsg(int userid, int user2_id, Integer page, Integer pageSize);

    void addMessage(Message message1);

    int addUnread(int user_id,int user2_id);

    void updateTime(int parseInt, int parseInt1);
}
