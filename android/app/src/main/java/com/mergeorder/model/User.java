package com.mergeorder.model;

/**
 * Created by song on 17-4-23.
 * <p>
 * user对象
 */
public class User {

    private int id;

    private String telephone;

    private String nickname;

    private String password;

    private String school;

    private String sex;

    private int successCount;

    private String iconUrl;

    private String backgroundUrl;

    //是否认证
    private boolean authentication;
    //学生证URL
    private String sic_url;

    public int getId() {
        return id;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getSchool() {
        return school;
    }

    public String getSex() {
        return sex;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", school='" + school + '\'' +
                ", sex='" + sex + '\'' +
                ", successCount=" + successCount +
                ", iconUrl='" + iconUrl + '\'' +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                '}';
    }
}
