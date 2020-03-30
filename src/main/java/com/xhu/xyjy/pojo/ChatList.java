package com.xhu.xyjy.pojo;

import java.sql.Timestamp;

public class ChatList {

    private  int  id;
    private  int  user_id;
    private  int  user2_id;
    private Timestamp last_time;
    private  int  is_online;
    private int unread;
    private  int status;

    public ChatList() {
    }

    public ChatList(int id, int user_id, int user2_id, Timestamp last_time, int is_online, int unread, int status) {
        this.id = id;
        this.user_id = user_id;
        this.user2_id = user2_id;
        this.last_time = last_time;
        this.is_online = is_online;
        this.unread = unread;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getLast_time() {
        return last_time;
    }

    public void setLast_time(Timestamp last_time) {
        this.last_time = last_time;
    }

    @Override
    public String toString() {
        return "ChatList{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", user2_id=" + user2_id +
                ", last_time=" + last_time +
                ", is_online=" + is_online +
                ", unread=" + unread +
                ", status=" + status +
                '}';
    }
}
