package com.xhu.xyjy.pojo;

public class Student {

    int id;
    int user_id;
    String school;
    String major;
    //学生证图片
    String pic;
    String status;


    public Student() {
    }

    public Student(int id, int user_id, String school, String major, String pic, String status) {
        this.id = id;
        this.user_id = user_id;
        this.school = school;
        this.major = major;
        this.pic = pic;
        this.status = status;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", pic='" + pic + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
