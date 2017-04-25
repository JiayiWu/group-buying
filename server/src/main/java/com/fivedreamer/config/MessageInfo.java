package com.fivedreamer.config;

/**
 * Created by Jiayiwu on 17/4/25.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class MessageInfo {
    boolean result;
    Object object;
    String reason;

    public MessageInfo(boolean result, String reason) {
        this.result = result;
        this.reason = reason;
    }

    public MessageInfo(boolean result, Object object, String reason) {
        this.result = result;
        this.object = object;
        this.reason = reason;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
