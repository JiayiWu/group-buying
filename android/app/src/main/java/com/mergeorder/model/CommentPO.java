package com.mergeorder.model;

import java.io.Serializable;

/**
 * Created by song on 17-5-4.
 * <p>
 * 保持与服务端一致
 * 比Comment多type、orderid
 */
public class CommentPO implements Serializable {

    private int id;
    private int formid;
    private int toid;
    private int type;
    private int orderid;
    private String content;
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

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
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
        return "CommentPO{" +
                "id=" + id +
                ", formid=" + formid +
                ", toid=" + toid +
                ", type=" + type +
                ", orderid=" + orderid +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
