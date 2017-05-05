package com.fivedreamer.vo;

/**
 * Created by Jiayiwu on 17/4/27.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class UserListVO {
    //粉丝ID
    private int id;
    //粉丝昵称
    private String userName;
    //粉丝URL
    private String iconUrl;
    //是否经过学生证认证
    private boolean authentication = false;


    public UserListVO() {
    }

    public UserListVO(int id, String userName, String iconUrl) {
        this.id = id;
        this.userName = userName;
        this.iconUrl = iconUrl;
    }

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

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }
}
