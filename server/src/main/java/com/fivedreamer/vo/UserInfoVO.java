package com.fivedreamer.vo;

import com.fivedreamer.model.Tag;

import java.util.LinkedList;
import java.util.List;

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
    //用于存储用户发布过的订单
    private List<GroupBuyOrderRecommendListVO> list = new LinkedList<GroupBuyOrderRecommendListVO>();
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

    public UserInfoVO() {
    }

    public UserInfoVO(String id, String telephone, String nickname, String school, String sex, int successCount, String iconurl, String backgroundurl, int fansCount, int focusCount, int postCount, int attendCount, int commentCount, List<GroupBuyOrderRecommendListVO> list,List<Tag> tags) {
        this.id = id;
        this.telephone = telephone;
        this.nickname = nickname;
        this.school = school;
        this.sex = sex;
        this.successCount = successCount;
        this.iconurl = iconurl;
        this.backgroundurl = backgroundurl;
        this.fansCount = fansCount;
        this.focusCount = focusCount;
        this.postCount = postCount;
        this.attendCount = attendCount;
        this.commentCount = commentCount;
        this.list = list;
        this.tags = tags;
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

    public List<GroupBuyOrderRecommendListVO> getList() {
        return list;
    }

    public void setList(List<GroupBuyOrderRecommendListVO> list) {
        this.list = list;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(int focusCount) {
        this.focusCount = focusCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(int attendCount) {
        this.attendCount = attendCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
