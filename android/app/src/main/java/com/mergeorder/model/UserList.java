package com.mergeorder.model;

import java.io.Serializable;

/**
 * Created by song on 17-5-1.
 *
 * 各式各样的用户列表中的对象
 */
public class UserList implements Serializable {

    private int id;

    private String userName;

    private String iconUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
