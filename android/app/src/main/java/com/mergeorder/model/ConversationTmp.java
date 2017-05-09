package com.mergeorder.model;

import com.hyphenate.chat.EMMessage;

/**
 * Created by song on 17-5-7.
 * <p>
 * 聊天回话
 * 插入数据专用
 */
public class ConversationTmp {

    private int portrait;

    private String name;

    private String time;

    private String content;

    /**
     * 未读数量
     */
    private int unreadNum;

    public ConversationTmp(int portrait, String name, String time, String content, int unreadNum) {
        this.portrait = portrait;
        this.name = name;
        this.time = time;
        this.content = content;
        this.unreadNum = unreadNum;
    }

    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(int unreadNum) {
        this.unreadNum = unreadNum;
    }
}
