package org.sang.bean;

/**
 * @author sang
 * @date 2018/1/26
 */
public class ChatResp {
    private String msg;
    private String from;

    public ChatResp() {
    }

    public ChatResp(String msg, String from) {
        this.msg = msg;
        this.from = from;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
