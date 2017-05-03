package com.fivedreamer.model;

/**
 * Created by Jiayiwu on 17/5/3.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Admin {

    private int id;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
