package com.xhu.xyjy.pojo;

import java.sql.Timestamp;

public class Friend {

    private  int id;
    private  int user_id ;
    private  int friend_id;
    private  int group_id;
    private  String  group_name;
    private Timestamp add_time;
    private  int status;

    public Friend() {
    }

    public Friend(int id, int user_id, int friend_id, int group_id, String group_name, Timestamp add_time, int status) {
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.group_id = group_id;
        this.group_name = group_name;
        this.add_time = add_time;
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

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Timestamp getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Timestamp add_time) {
        this.add_time = add_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", friend_id=" + friend_id +
                ", group_id=" + group_id +
                ", group_name='" + group_name + '\'' +
                ", add_time=" + add_time +
                ", status=" + status +
                '}';
    }
}
