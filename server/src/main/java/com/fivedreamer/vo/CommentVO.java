package com.fivedreamer.vo;

import com.fivedreamer.model.Comment;
import com.fivedreamer.utils.DataUtil;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class CommentVO {

    //评论的编号
    int id;

    //评论人ID
    int formid;
    //评论人的昵称
    String formName;
    //评论人的图像URL
    String formIconUrl;
    //被评论人ID(为-1则表示为评论)
    int toid;
    //被评论人的昵称(为Null则表示该信息为评论,否则为回复)
    String toName;
    //被评论人的图像URL(为Null则表示该信息为评论,否则为回复)
    String toIconUrl;
    //评论内容
    String content;
    //评论时间
    long time;



    public CommentVO() {
    }

    public CommentVO(Comment comment) {
        this.id = comment.getId();
        this.formid = comment.getFormid();
        this.formName = comment.getFormName();
        this.formIconUrl = comment.getFormIconUrl();
        this.toid = comment.getToid();
        this.toName = comment.getToName();
        this.toIconUrl = comment.getToIconUrl();
        this.content = comment.getContent();
        this.time = comment.getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getFormid() {
        return formid;
    }

    public void setFormid(int formid) {
        this.formid = formid;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }
}
