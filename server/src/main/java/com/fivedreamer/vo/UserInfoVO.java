package com.fivedreamer.vo;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class UserInfoVO {
    //用户ID
    private String id;
    //绑定电话
    private String telephone;
    //昵称
    private String nickname;
    //用户学校
    private String school;
    //用户性别
    private String sex;
    //拼单成功次数
    private int successCount;
    //用户头像URL
    private String iconurl;
    //用户背景图片URL
    private String backgroundurl;

    public UserInfoVO() {
    }

    public UserInfoVO(String id, String telephone, String nickname, String school, String sex, int successCount, String iconurl, String backgroundurl) {
        this.id = id;
        this.telephone = telephone;
        this.nickname = nickname;
        this.school = school;
        this.sex = sex;
        this.successCount = successCount;
        this.iconurl = iconurl;
        this.backgroundurl = backgroundurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getBackgroundurl() {
        return backgroundurl;
    }

    public void setBackgroundurl(String backgroundurl) {
        this.backgroundurl = backgroundurl;
    }
}
