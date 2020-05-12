package com.wedding.model.po;

public class ChatHistory {
    private Integer id;

    private Integer userId;

    private Integer chattedUserId;

    private Byte isagree;

    private String userAisle;

    private String chattedUserAisle;

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

    public Byte getIsagree() {
        return isagree;
    }

    public void setIsagree(Byte isagree) {
        this.isagree = isagree;
    }

    public String getUserAisle() {
        return userAisle;
    }

    public void setUserAisle(String userAisle) {
        this.userAisle = userAisle == null ? null : userAisle.trim();
    }

    public String getChattedUserAisle() {
        return chattedUserAisle;
    }

    public void setChattedUserAisle(String chattedUserAisle) {
        this.chattedUserAisle = chattedUserAisle == null ? null : chattedUserAisle.trim();
    }
}