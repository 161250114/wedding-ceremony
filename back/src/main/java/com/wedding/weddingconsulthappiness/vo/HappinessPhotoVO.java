package com.wedding.weddingconsulthappiness.vo;

public class HappinessPhotoVO {
    private int id;

    private int happinessId;

    private String photo;

    public int getId() {
        return id;
    }

    public HappinessPhotoVO() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHappinessId() {
        return happinessId;
    }

    public void setHappinessId(int happinessId) {
        this.happinessId = happinessId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
