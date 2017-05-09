package com.mergeorder.model;

import java.io.Serializable;

/**
 * Created by song on 17-5-2.
 *
 * 评论
 */
public class Comment implements Serializable {
    //评论的编号
    private int id;
    //评论人ID
    private int formid;
    //评论人的昵称
    private String formName;
    //评论人的图像URL
    private String formIconUrl;
    //被评论人ID(为-1则表示为评论)
    private int toid;
    //被评论人的昵称(为Null则表示该信息为评论,否则为回复)
    private String toName;
    //被评论人的图像URL(为Null则表示该信息为评论,否则为回复)
    private String toIconUrl;
    //评论内容
    private String content;
    //评论时间
    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormid() {
        return formid;
    }

    public void setFormid(int formid) {
        this.formid = formid;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormIconUrl() {
        return formIconUrl;
    }

    public void setFormIconUrl(String formIconUrl) {
        this.formIconUrl = formIconUrl;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToIconUrl() {
        return toIconUrl;
    }

    public void setToIconUrl(String toIconUrl) {
        this.toIconUrl = toIconUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", formid=" + formid +
                ", formName='" + formName + '\'' +
                ", formIconUrl='" + formIconUrl + '\'' +
                ", toid=" + toid +
                ", toName='" + toName + '\'' +
                ", toIconUrl='" + toIconUrl + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
