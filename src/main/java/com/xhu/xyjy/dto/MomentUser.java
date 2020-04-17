package com.xhu.xyjy.dto;

import java.sql.Timestamp;

public class MomentUser {
    private  int id;
    private  int user_id;
    private  String  description;
    private  String picture;
    private  String video;
    private Timestamp time;
    private  String  tag;
    private  int  view_count;
    private  int  like_count;
    private  int  comment_count;
    private String user_name;
    private String user_picture;

    @Override
    public String toString() {
        return "MomentUser{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", time=" + time +
                ", tag='" + tag + '\'' +
                ", view_count=" + view_count +
                ", like_count=" + like_count +
                ", comment_count=" + comment_count +
                ", user_name='" + user_name + '\'' +
                ", user_picture='" + user_picture + '\'' +
                '}';
    }

    public MomentUser() {
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
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
}
