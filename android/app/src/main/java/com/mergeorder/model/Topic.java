package com.mergeorder.model;

import java.io.Serializable;

/**
 * Created by song on 17-5-2.
 * <p>
 * 发布的帖子
 */
public class Topic implements Serializable {

    //订单号
    private int id;
    //订单类型 如拼车 拼团等 type 0为拼车 1为拼课 2为拼邮 3拼团
    private int ordertype;
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
    //订单本地时间表述 如2017-04-26 13:26:16
    private String timeformate;
    //订单发布时间long类型
    private long time;
    //订单内容 0则为拼车方向 1为拼课分类 同理 2 3
    private String type;
    //留言数量
    private int leaveMessageCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
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

    public String getTimeformate() {
        return timeformate;
    }

    public void setTimeformate(String timeformate) {
        this.timeformate = timeformate;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLeaveMessageCount() {
        return leaveMessageCount;
    }

    public void setLeaveMessageCount(int leaveMessageCount) {
        this.leaveMessageCount = leaveMessageCount;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", ordertype=" + ordertype +
                ", username='" + username + '\'' +
                ", usericonurl='" + usericonurl + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", location='" + location + '\'' +
                ", timeformate='" + timeformate + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", leaveMessageCount=" + leaveMessageCount +
                '}';
    }
}
