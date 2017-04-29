package com.fivedreamer.model;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Advertisement {

    private int id;

    private String imgURL;

    private String contents;

    private String targetURL;

    public Advertisement() {
    }

    public Advertisement(String imgURL, String contents, String targetURL) {
        this.imgURL = imgURL;
        this.contents = contents;
        this.targetURL = targetURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
