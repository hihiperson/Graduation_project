package com.ppw.graduation.project.model.dto;

import java.io.Serializable;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/3/21 20:47
 */

//临时用户表
public class UserDTO implements Serializable {

    private int userId;
    private String uname;
    private String picture;

    public UserDTO() {
    }

    public UserDTO(int userId, String uname, String picture) {
        this.userId = userId;
        this.uname = uname;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", uname='" + uname + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
