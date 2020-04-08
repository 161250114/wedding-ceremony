package com.wedding.model.po;

public class Happiness_photo {
    private Integer id;

    private Integer happinessId;

    private byte[] photo;

    public Integer getId() {
        return id;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}