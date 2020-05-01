package com.wedding.model.po;

import java.util.Date;

public class ChatHistory {
    private Integer id;

    private Integer userId;

    private Integer chattedUserId;

    private Date beginTime;

    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChattedUserId() {
        return chattedUserId;
    }

    public void setChattedUserId(Integer chattedUserId) {
        this.chattedUserId = chattedUserId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}