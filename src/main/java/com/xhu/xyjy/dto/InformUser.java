package com.xhu.xyjy.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InformUser {
    private  int id;
    private  int user1_id;
    private  int user2_id;
    private  int moment_id;
    private  String content;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;
    private  int type;
    private String user1_name;
    private String user2_name;
    private String user1_picture;
    private String user2_picture;

    public InformUser() {
    }


    public InformUser(int id, int user1_id, int user2_id, int moment_id, String content, Date time, int type, String user1_name, String user2_name, String user1_picture, String user2_picture) {
        this.id = id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.moment_id = moment_id;
        this.content = content;
        this.time = time;
        this.type = type;
        this.user1_name = user1_name;
        this.user2_name = user2_name;
        this.user1_picture = user1_picture;
        this.user2_picture = user2_picture;
    }


    @Override
    public String toString() {
        return "InformUser{" +
                "id=" + id +
                ", user1_id=" + user1_id +
                ", user2_id=" + user2_id +
                ", moment_id=" + moment_id +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", type=" + type +
                ", user1_name='" + user1_name + '\'' +
                ", user2_name='" + user2_name + '\'' +
                ", user1_picture='" + user1_picture + '\'' +
                ", user2_picture='" + user2_picture + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(int moment_id) {
        this.moment_id = moment_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUser1_name() {
        return user1_name;
    }

    public void setUser1_name(String user1_name) {
        this.user1_name = user1_name;
    }

    public String getUser2_name() {
        return user2_name;
    }

    public void setUser2_name(String user2_name) {
        this.user2_name = user2_name;
    }

    public String getUser1_picture() {
        return user1_picture;
    }

    public void setUser1_picture(String user1_picture) {
        this.user1_picture = user1_picture;
    }

    public String getUser2_picture() {
        return user2_picture;
    }

    public void setUser2_picture(String user2_picture) {
        this.user2_picture = user2_picture;
    }
}
