package com.mergeorder.model;

import java.io.Serializable;

/**
 * Created by Jiayiwu on 17/4/30.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Tag implements Serializable {
    private int id;

    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
