package com.mergeorder.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by song on 17-5-1.
 *
 * 用户信息，包含关注、粉丝等
 */
public class UserInfo implements Serializable {
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
    //粉丝数量
    private int fansCount;
    //关注人数数量
    private int focusCount;
    //我发布的数量
    private int postCount;
    //我拼到的数量
    private int attendCount;
    //我评价的数量
    private int commentCount;
    //用户相关的标签
    private List<Tag> tags;
    //是否经过学生证认证
    private boolean authentication;

    public String getId() {
        return id;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNickname() {
        return nickname;
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

    public String getIconurl() {
        return iconurl;
    }

    public String getBackgroundurl() {
        return backgroundurl;
    }

    public int getFansCount() {
        return fansCount;
    }

    public int getFocusCount() {
        return focusCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public int getAttendCount() {
        return attendCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public void setBackgroundurl(String backgroundurl) {
        this.backgroundurl = backgroundurl;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public void setFocusCount(int focusCount) {
        this.focusCount = focusCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public void setAttendCount(int attendCount) {
        this.attendCount = attendCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", telephone='" + telephone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", school='" + school + '\'' +
                ", sex='" + sex + '\'' +
                ", successCount=" + successCount +
                ", iconurl='" + iconurl + '\'' +
                ", backgroundurl='" + backgroundurl + '\'' +
                ", fansCount=" + fansCount +
                ", focusCount=" + focusCount +
                ", postCount=" + postCount +
                ", attendCount=" + attendCount +
                ", commentCount=" + commentCount +
                ", tags=" + tags +
                '}';
    }
}
