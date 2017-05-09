package com.mergeorder.model;

/**
 * Created by Jiayiwu on 17/4/25.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class MessageInfo {
    private boolean result;
    private Object object;
    private String reason;

    public MessageInfo() {

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

    @Override
    public String toString() {
        return "MessageInfo{" +
                "result=" + result +
                ", object=" + object +
                ", reason='" + reason + '\'' +
                '}';
    }
}
