package com.fivedreamer.model;

/**
 * Created by Jiayiwu on 17/5/1.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Relationship {

    private int id;
    private int ownerid;
    private int fansid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    public int getFansid() {
        return fansid;
    }

    public void setFansid(int fansid) {
        this.fansid = fansid;
    }
}
