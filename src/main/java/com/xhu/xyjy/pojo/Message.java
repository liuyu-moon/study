package com.xhu.xyjy.pojo;

import java.sql.Timestamp;

public class Message {

    private  int id;
    private  int user_id;
    private  int user2_id;
    private  String content;
    private Timestamp time;
    private  int type;
    private  int  is_last;
    private int status;

    public Message() {
    }


    public Message(int id, int user_id, int user2_id, String content, Timestamp time, int type, int is_last, int status) {
        this.id = id;
        this.user_id = user_id;
        this.user2_id = user2_id;
        this.content = content;
        this.time = time;
        this.type = type;
        this.is_last = is_last;
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", user2_id=" + user2_id +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", type=" + type +
                ", is_last=" + is_last +
                ", status=" + status +
                '}';
    }
}
