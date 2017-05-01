package com.fivedreamer.model;

/**
 * Created by Jiayiwu on 17/5/1.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class CommentPO {

    private int id;
    private int formid;
    private int toid;
    private int type;
    private int orderid;
    private String content;
    private long time;


    public CommentPO() {
    }

    public CommentPO(int formid, int type, int orderid, String content, long time) {
        this.formid = formid;
        this.type = type;
        this.orderid = orderid;
        this.content = content;
        this.time = time;
    }


    public CommentPO(int formid, int toid, int type, int orderid, String content, long time) {
        this.formid = formid;
        this.toid = toid;
        this.type = type;
        this.orderid = orderid;
        this.content = content;
        this.time = time;
    }

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

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
