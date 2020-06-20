package com.xhu.xyjy.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RecordUser {

    int id;
    int user1_id;
    int user2_id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;
    private  int type;
    private  int moment_id;
    private String user_name;

    private String user_picture;


    public RecordUser() {
    }

    public RecordUser(int id, int user1_id, int user2_id, Date time, int type, int moment_id, String user_name, String user_picture) {
        this.id = id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.time = time;
        this.type = type;
        this.moment_id = moment_id;
        this.user_name = user_name;
        this.user_picture = user_picture;
    }

    public int getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(int moment_id) {
        this.moment_id = moment_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(int user1_id) {
        this.user1_id = user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    @Override
    public String toString() {
        return "RecordUser{" +
                "id=" + id +
                ", user1_id=" + user1_id +
                ", user2_id=" + user2_id +
                ", time=" + time +
                ", type=" + type +
                ", moment_id=" + moment_id +
                ", user_name='" + user_name + '\'' +
                ", user_picture='" + user_picture + '\'' +
                '}';
    }
}
