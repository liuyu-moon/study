package com.xhu.xyjy.dto;

import java.sql.Timestamp;

public class FriendInfo {

    private int user_id;
    private String user_name;
    private String user_pwd;
    private String user_picture;
    private int user_type;
    private int user_status;
    private Timestamp user_addtime;
    private Timestamp user_logintime;
    private String user_phone;
    private String user_sex;
    private String user_school;
    private int user_momentsnum;//朋友圈数量

    private  int group_id;
    private  String  group_name;
    private Timestamp add_time;

    public FriendInfo() {
    }

    public FriendInfo(int user_id, String user_name, String user_pwd, String user_picture, int user_type, int user_status, Timestamp user_addtime, Timestamp user_logintime, String user_phone, String user_sex, String user_school, int user_momentsnum, int group_id, String group_name, Timestamp add_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.user_picture = user_picture;
        this.user_type = user_type;
        this.user_status = user_status;
        this.user_addtime = user_addtime;
        this.user_logintime = user_logintime;
        this.user_phone = user_phone;
        this.user_sex = user_sex;
        this.user_school = user_school;
        this.user_momentsnum = user_momentsnum;
        this.group_id = group_id;
        this.group_name = group_name;
        this.add_time = add_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public Timestamp getUser_addtime() {
        return user_addtime;
    }

    public void setUser_addtime(Timestamp user_addtime) {
        this.user_addtime = user_addtime;
    }

    public Timestamp getUser_logintime() {
        return user_logintime;
    }

    public void setUser_logintime(Timestamp user_logintime) {
        this.user_logintime = user_logintime;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_school() {
        return user_school;
    }

    public void setUser_school(String user_school) {
        this.user_school = user_school;
    }

    public int getUser_momentsnum() {
        return user_momentsnum;
    }

    public void setUser_momentsnum(int user_momentsnum) {
        this.user_momentsnum = user_momentsnum;
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

    @Override
    public String toString() {
        return "FriendInfo{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_picture='" + user_picture + '\'' +
                ", user_type=" + user_type +
                ", user_status=" + user_status +
                ", user_addtime=" + user_addtime +
                ", user_logintime=" + user_logintime +
                ", user_phone='" + user_phone + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_school='" + user_school + '\'' +
                ", user_momentsnum=" + user_momentsnum +
                ", group_id=" + group_id +
                ", group_name='" + group_name + '\'' +
                ", add_time=" + add_time +
                '}';
    }
}
