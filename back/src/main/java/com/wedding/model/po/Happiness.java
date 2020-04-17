package com.wedding.model.po;

import java.util.Date;

public class Happiness {
    private Integer id;

    private Integer senderId;

    private Date time;

    private String content;

    public Happiness() {
    }

    public Happiness(Integer id, Integer senderId, Date time, String content, Integer likes, Integer state) {
        this.id = id;
        this.senderId = senderId;
        this.time = time;
        this.content = content;
        this.likes = likes;
        this.state = state;
    }

    private Integer likes;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}