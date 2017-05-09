package com.mergeorder.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by song on 17-5-3.
 * <p>
 * topic 详情
 */
public class TopicDetail implements Serializable {

    //订单号
    private int id;
    //订单发起人ID
    private int userid;
    //订单发起人昵称
    private String username;
    //订单发起人头像
    private String usericonurl;
    //订单主题
    private String title;
    //订单内容
    private String content;
    //订单发布者位置
    private String location;
    //订单时间
    private long time;

    //订单详细图片信息
    private String[] imgPath;

    //拼车方向, 拼课分类 等
    private String type;
    //留言
    private List<Comment> comments = new LinkedList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsericonurl() {
        return usericonurl;
    }

    public void setUsericonurl(String usericonurl) {
        this.usericonurl = usericonurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String[] getImgPath() {
        return imgPath;
    }

    public void setImgPath(String[] imgPath) {
        this.imgPath = imgPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
