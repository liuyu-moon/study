package com.xhu.xyjy.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Inform {
    private  int id;
    private  int user1_id;
    private  int user2_id;
    private  int moment_id;
    private  String content;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;
    private  int type;

    public Inform() {
    }

    public Inform(int id, int user1_id, int user2_id, int moment_id, String content, Date time, int type) {
        this.id = id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.moment_id = moment_id;
        this.content = content;
        this.time = time;
        this.type = type;
    }

    public int getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(int moment_id) {
        this.moment_id = moment_id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "id=" + id +
                ", user1_id=" + user1_id +
                ", user2_id=" + user2_id +
                ", moment_id=" + moment_id +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", type=" + type +
                '}';
    }
}
