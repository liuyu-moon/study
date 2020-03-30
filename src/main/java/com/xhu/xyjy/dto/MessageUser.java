package com.xhu.xyjy.dto;

import java.sql.Timestamp;

public class MessageUser {

    private  int id;
    private  int user_id;
    private  int user2_id;
    private  String content;
    private Timestamp time;
    private  int type;
    private  int  is_last;
    private int status;
    private String user_name;
    private String user2_name;
    private String user_picture;
    private String user2_picture;

    public MessageUser() {
    }

    public MessageUser(int id, int user_id, int user2_id, String content, Timestamp time, int type, int is_last, int status, String user_name, String user2_name, String user_picture, String user2_picture) {
        this.id = id;
        this.user_id = user_id;
        this.user2_id = user2_id;
        this.content = content;
        this.time = time;
        this.type = type;
        this.is_last = is_last;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIs_last() {
        return is_last;
    }

    public void setIs_last(int is_last) {
        this.is_last = is_last;
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
        return "MessageUser{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", user2_id=" + user2_id +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", type=" + type +
                ", is_last=" + is_last +
                ", status=" + status +
                ", user_name='" + user_name + '\'' +
                ", user2_name='" + user2_name + '\'' +
                ", user_picture='" + user_picture + '\'' +
                ", user2_picture='" + user2_picture + '\'' +
                '}';
    }
}
