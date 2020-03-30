package com.xhu.xyjy.dao;

import com.xhu.xyjy.dto.ChatUser;
import com.xhu.xyjy.dto.MessageUser;
import com.xhu.xyjy.pojo.ChatList;
import com.xhu.xyjy.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ChatMapper {

    @Select("select * from chatlist where user_id=#{user_id} and user2_id=#{user2_id}")
    ChatList selectChatList(int user_id,int user2_id);


    @Insert("insert into chatlist (user_id,user2_id,last_time) values(#{user_id},#{user2_id},#{last_time})")
    boolean addChat(ChatList chatList);

    //根据ID查出聊天列表
    @Select("select chatlist.*,user.user_name as user2_name,user.user_picture as user2_picture from chatlist  INNER  JOIN user on user.user_id=chatlist.user2_id and chatlist.user_id = #{user_id} ORDER BY last_time Desc")
    List<ChatUser> selectChatListInfo(int user_id);

    @Insert("insert into message (user_id,user2_id,content,time,type)values(#{user_id},#{user2_id},#{content},#{time},#{type})")
        boolean addMessage(Message message);
    //查历史消息
    @Select("select message.*,u1.user_name,u1.user_picture,u2.user_name as user2_name,u2.user_picture as user2_picture from message " +
            "Inner JOIN user as u1  on message.user_id=u1.user_id " +
            "Inner JOIN user as u2 on message.user_id=u2.user_id " +
            "and (message.user_id= 3 and message.user2_id=1 or message.user_id= 1 and message.user2_id=3) ")
    List<MessageUser> selectHistoryMessage(int user_id, int user2_id);

    @Update("update chatlist set unread =unread +1 where user_id =#{user2_id} and user2_id=#{user_id}")
    void addUnread(int user_id, int user2_id);

    @Update("update chatlist set last_time=#{last_time} where user_id=#{user_id} and user2_id=#{user2_id}")
    void updateLastTime(int user_id, int user2_id, Timestamp last_time);
}
