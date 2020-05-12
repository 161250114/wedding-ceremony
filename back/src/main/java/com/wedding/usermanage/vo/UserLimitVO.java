package com.wedding.usermanage.vo;

import java.io.Serializable;

public class UserLimitVO implements Serializable {
    private int limitBrowse;//0为所有人可见，1为符合择偶标准可见，2为所有人不可见

    private int limitMessage;//0为接收系统短信，1为不接受系统短信

    private int limitFapply;//0为所有人可申请，1为拒绝申请

    private int limitDapply;//0为所有人可申请，1为拒绝申请

    public UserLimitVO(int limitBrowse, int limitMessage, int limitFapply, int limitDapply) {
        this.limitBrowse = limitBrowse;
        this.limitMessage = limitMessage;
        this.limitFapply = limitFapply;
        this.limitDapply = limitDapply;
    }

    public UserLimitVO() {
    }

    public int getLimitBrowse() {
        return limitBrowse;
    }

    public void setLimitBrowse(int limitBrowse) {
        this.limitBrowse = limitBrowse;
    }

    public int getLimitMessage() {
        return limitMessage;
    }

    public void setLimitMessage(int limitMessage) {
        this.limitMessage = limitMessage;
    }

    public int getLimitFapply() {
        return limitFapply;
    }

    public void setLimitFapply(int limitFapply) {
        this.limitFapply = limitFapply;
    }

    public int getLimitDapply() {
        return limitDapply;
    }

    public void setLimitDapply(int limitDapply) {
        this.limitDapply = limitDapply;
    }
}
