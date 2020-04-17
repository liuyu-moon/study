package com.xhu.xyjy.pojo;

import java.sql.Time;
import java.sql.Timestamp;

public class Moment {

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

    public Moment() {
    }

    public Moment(int id, int user_id, String description, String picture, String video, Timestamp time, String tag, int view_count, int like_count, int comment_count) {
        this.id = id;
        this.user_id = user_id;
        this.description = description;
        this.picture = picture;
        this.video = video;
        this.time = time;
        this.tag = tag;
        this.view_count = view_count;
        this.like_count = like_count;
        this.comment_count = comment_count;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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


    @Override
    public String toString() {
        return "Moment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", video='" + video + '\'' +
                ", time=" + time +
                ", tag='" + tag + '\'' +
                ", view_count=" + view_count +
                ", like_count=" + like_count +
                ", comment_count=" + comment_count +
                '}';
    }
}
