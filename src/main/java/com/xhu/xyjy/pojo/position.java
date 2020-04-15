package com.xhu.xyjy.pojo;

public class position {
    int id;
    int user_id;
    //经度
    double lng;
    //纬度
    double lat;

    public position() {
    }

    public position(int id, int user_id, double lng, double lat) {
        this.id = id;
        this.user_id = user_id;
        this.lng = lng;
        this.lat = lat;
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

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "position{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
