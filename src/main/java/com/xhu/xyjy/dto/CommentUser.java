package com.xhu.xyjy.dto;

import java.sql.Timestamp;

public class CommentUser {
    private  int id;
    private  int moment_id;
    private  int comment_id;
    private  int user_id;
    private  int user2_id;
    private  String content;
    private  int level;
    private Timestamp creat_time;
    private  int like_count;
    private  int status;
    private String user_name;
    private String user_picture;
    private String user2_name;
    private String user2_picture;

    public CommentUser() {
    }

    public CommentUser(int id, int moment_id, int comment_id, int user_id, int user2_id, String content, int level, Timestamp creat_time, int like_count, int status, String user_name, String user_picture, String user2_name, String user2_picture) {
        this.id = id;
        this.moment_id = moment_id;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.user2_id = user2_id;
        this.content = content;
        this.level = level;
        this.creat_time = creat_time;
        this.like_count = like_count;
        this.status = status;
        this.user_name = user_name;
        this.user_picture = user_picture;
        this.user2_name = user2_name;
        this.user2_picture = user2_picture;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }

    public String getUser2_name() {
        return user2_name;
    }

    public void setUser2_name(String user2_name) {
        this.user2_name = user2_name;
    }

    public String getUser2_picture() {
        return user2_picture;
    }

    public void setUser2_picture(String user2_picture) {
        this.user2_picture = user2_picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(int moment_id) {
        this.moment_id = moment_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Timestamp getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Timestamp creat_time) {
        this.creat_time = creat_time;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
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

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    @Override
    public String toString() {
        return "CommentUser{" +
                "id=" + id +
                ", moment_id=" + moment_id +
                ", comment_id=" + comment_id +
                ", user_id=" + user_id +
                ", user2_id=" + user2_id +
                ", content='" + content + '\'' +
                ", level=" + level +
                ", creat_time=" + creat_time +
                ", like_count=" + like_count +
                ", status=" + status +
                ", user_name='" + user_name + '\'' +
                ", user_picture='" + user_picture + '\'' +
                ", user2_name='" + user2_name + '\'' +
                ", user2_picture='" + user2_picture + '\'' +
                '}';
    }
}
