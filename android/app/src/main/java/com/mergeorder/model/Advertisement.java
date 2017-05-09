package com.mergeorder.model;

/**
 * Created by song on 17-4-29.
 *
 * 首页轮播广告
 */
public class Advertisement {

    private int id;

    private String imgURL;

    private String contents;

    private String targetURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", imgURL='" + imgURL + '\'' +
                ", contents='" + contents + '\'' +
                ", targetURL='" + targetURL + '\'' +
                '}';
    }
}