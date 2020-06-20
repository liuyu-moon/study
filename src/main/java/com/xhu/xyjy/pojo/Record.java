package com.xhu.xyjy.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Record {
    int id;
    int user1_id;
    int user2_id;
    int moment_id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;
    int type;

    public Record() {
    }

    public Record(int id, int user1_id, int user2_id, int moment_id, Date time, int type) {
        this.id = id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.moment_id = moment_id;
        this.time = time;
        this.type = type;
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

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", user1_id=" + user1_id +
                ", user2_id=" + user2_id +
                ", moment_id=" + moment_id +
                ", time=" + time +
                ", type=" + type +
                '}';
    }
}
