package com.wedding.model.po;

public class CheckHistory {
    private Integer id;

    private Integer userId;

    private Integer checkedUserId;

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

    public Integer getCheckedUserId() {
        return checkedUserId;
    }

    public void setCheckedUserId(Integer checkedUserId) {
        this.checkedUserId = checkedUserId;
    }
}