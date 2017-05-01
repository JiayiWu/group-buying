package com.fivedreamer.vo;

import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.utils.DataUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class ClassOrderDetailVO {
    //订单号
    private int id;
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

    //订单详细图片信息
    private String[] imgPath;

    //拼课类型
    private String type;
    //留言
    private List<CommentVO> comments = new LinkedList<CommentVO>();


    public ClassOrderDetailVO(CommonOrder commonOrder) {
        this.id = commonOrder.getId();
        this.username = commonOrder.getUsername();
        this.usericonurl = commonOrder.getUsericonurl();
        this.title = commonOrder.getTitle();
        this.content = commonOrder.getContent();
        this.location = commonOrder.getLocation();
        this.timeformate = DataUtil.getLongToDateString(commonOrder.getTime());
        switch (commonOrder.getType()){
            case 0:
                this.type = "雅思托福";
                break;
            case 1:
                this.type = "BEC托业";
                break;
            case 2:
                this.type = "考研";
                break;
            case 3:
                this.type = "考证";
                break;
            case 4:
                this.type = "小语种";
                break;
            case 5:
                this.type = "考驾照";
                break;
            case 6:
                this.type = "来健身";
                break;
            case 7:
                this.type = "其他";
                break;
        }
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

    public String getTimeformate() {
        return timeformate;
    }

    public void setTimeformate(String timeformate) {
        this.timeformate = timeformate;
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
