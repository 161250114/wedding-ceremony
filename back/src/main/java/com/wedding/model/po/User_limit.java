package com.wedding.model.po;

public class User_limit {
    private Integer id;

    private Integer userid;

    private Byte limitBrowse;//0为所有人可见，1为符合择偶标准可见，2为所有人不可见

    private Byte limitMessage;//0为接收系统短信，1为不接受系统短信

    private Byte limitFapply;//0为所有人可申请，1为拒绝申请

    private Byte limitDapply;//0为所有人可申请，1为拒绝申请

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Byte getLimitBrowse() {
        return limitBrowse;
    }

    public void setLimitBrowse(Byte limitBrowse) {
        this.limitBrowse = limitBrowse;
    }

    public Byte getLimitMessage() {
        return limitMessage;
    }

    public void setLimitMessage(Byte limitMessage) {
        this.limitMessage = limitMessage;
    }

    public Byte getLimitFapply() {
        return limitFapply;
    }

    public void setLimitFapply(Byte limitFapply) {
        this.limitFapply = limitFapply;
    }

    public Byte getLimitDapply() {
        return limitDapply;
    }

    public void setLimitDapply(Byte limitDapply) {
        this.limitDapply = limitDapply;
    }
}
