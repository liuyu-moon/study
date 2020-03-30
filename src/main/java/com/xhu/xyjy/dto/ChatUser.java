package com.xhu.xyjy.dto;

import java.sql.Timestamp;

public class ChatUser {

    private  int  id;
    private  int  user_id;
    private  int  user2_id;
    private Timestamp last_time;
    private  int  is_online;
    private int unread;
    private  int status;
    private  String user_name;
    private  String user2_name;
    private   String user_picture;
    private   String user2_picture;

    public ChatUser() {
    }

    public ChatUser(int id, int user_id, int user2_id, Timestamp last_time, int is_online, int unread, int status, String user_name, String user2_name, String user_picture, String user2_picture) {
        this.id = id;
        this.user_id = user_id;
        this.user2_id = user2_id;
        this.last_time = last_time;
        this.is_online = is_online;
        this.unread = unread;
        this.status = status;
        this.user_name = user_name;
        this.user2_name = user2_name;
        this.user_picture = user_picture;
        this.user2_picture = user2_picture;
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

    public Timestamp getLast_time() {
        return last_time;
    }

    public void setLast_time(Timestamp last_time) {
        this.last_time = last_time;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser2_name() {
        return user2_name;
    }

    public void setUser2_name(String user2_name) {
        this.user2_name = user2_name;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    public String getUser2_picture() {
        return user2_picture;
    }

    public void setUser2_picture(String user2_picture) {
        this.user2_picture = user2_picture;
    }

    @Override
    public String toString() {
        return "ChatUser{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", user2_id=" + user2_id +
                ", last_time=" + last_time +
                ", is_online=" + is_online +
                ", unread=" + unread +
                ", status=" + status +
                ", user_name='" + user_name + '\'' +
                ", user2_name='" + user2_name + '\'' +
                ", user_picture='" + user_picture + '\'' +
                ", user2_picture='" + user2_picture + '\'' +
                '}';
    }
}
