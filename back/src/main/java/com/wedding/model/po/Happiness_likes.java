package com.wedding.model.po;

public class Happiness_likes {
    private Integer id;

    private Integer happinessId;

    private Integer likeId;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public Happiness_likes(Integer id, Integer happinessId, Integer likeId, Integer state) {
        this.id = id;
        this.happinessId = happinessId;
        this.likeId = likeId;
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Happiness_likes() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHappinessId() {
        return happinessId;
    }

    public void setHappinessId(Integer happinessId) {
        this.happinessId = happinessId;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }
}