package com.mergeorder.model;

import com.hyphenate.chat.EMMessage;

/**
 * Created by song on 17-5-7.
 * <p>
 * 聊天回话
 */
public class Conversation {

    /**
     * 聊天对象信息 */
    private UserInfo userInfo;

    /**
     * 最后一条消息
     */
    private EMMessage message;

    /**
     * 未读数量
     */
    private int unreadNum;

    public Conversation(UserInfo userInfo, EMMessage message, int unreadNum) {
        this.userInfo = userInfo;
        this.message = message;
        this.unreadNum = unreadNum;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public EMMessage getMessage() {
        return message;
    }

    public void setMessage(EMMessage message) {
        this.message = message;
    }

    public int getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(int unreadNum) {
        this.unreadNum = unreadNum;
    }
}
