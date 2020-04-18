package com.wedding.model.po;

public class Comment {
    private Integer id;

    private Integer happinessId;

    private Integer senderId;

    private String content;

    public Comment() {
    }

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comment(Integer id, Integer happinessId, Integer senderId, String content, Integer state) {
        this.id = id;
        this.happinessId = happinessId;
        this.senderId = senderId;
        this.content = content;
        this.state = state;
    }

    public Integer getHappinessId() {
        return happinessId;
    }

    public void setHappinessId(Integer happinessId) {
        this.happinessId = happinessId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}