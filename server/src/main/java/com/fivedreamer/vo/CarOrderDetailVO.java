package com.fivedreamer.vo;

import com.fivedreamer.model.CarOrder;
import com.fivedreamer.utils.DataUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class CarOrderDetailVO {
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
    //订单本地时间表述 如2017-04-26 13:26:16
    private long time;

    //订单详细图片信息
    private String[] imgPath;

    //拼车方向
    private String type;
    //留言
    private List<CommentVO> comments = new LinkedList<CommentVO>();


    public CarOrderDetailVO() {
    }

    public CarOrderDetailVO(CarOrder carOrder) {
        this.id = carOrder.getId();
        this.userid = carOrder.getUserid();
        this.username = carOrder.getUsername();
        this.usericonurl = carOrder.getUsericonurl();
        this.title = carOrder.getTitle();
        this.content = carOrder.getContent();
        this.location = carOrder.getLocation();
        this.time = carOrder.getTime();
        this.type = carOrder.getDirection();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public List<CommentVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }

    public String[] getImgPath() {
        return imgPath;
    }

    public void setImgPath(String[] imgPath) {
        this.imgPath = imgPath;
    }
}
