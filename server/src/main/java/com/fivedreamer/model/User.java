package com.fivedreamer.model;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class User {

    int id;

    String telephone;

    String nickname;

    String password;

    String school;

    String sex;

    int successCount;

    String iconUrl;

    String backgroundUrl;

    public User() {
    }

    public User(int id, String telephone, String nickname, String password, String school, String sex, int successCount, String iconUrl, String backgroundUrl) {
        this.id = id;
        this.telephone = telephone;
        this.nickname = nickname;
        this.password = password;
        this.school = school;
        this.sex = sex;
        this.successCount = successCount;
        this.iconUrl = iconUrl;
        this.backgroundUrl = backgroundUrl;
    }

    public User(String telephone, String nickname, String password, String school, String sex, String iconUrl, String backgroundUrl) {
        this.telephone = telephone;
        this.nickname = nickname;
        this.password = password;
        this.school = school;
        this.sex = sex;
        this.iconUrl = iconUrl;
        this.backgroundUrl = backgroundUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }
}
