package com.wedding.model.po;

public class User_limit {
    private Integer id;

    private Integer userid;

    private Byte limitBrowse;

    private Byte limitMessage;

    private Byte limitFapply;

    private Byte limitDapply;

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