package com.wedding.usermanage.vo;

import java.io.Serializable;

public class AlbumVO implements Serializable {
    int albumid;
    int currentNumber;
    int maxNumber;
    PhotoVO[] photos;

    public AlbumVO(int albumid, int currentNumber, int maxNumber,PhotoVO[] photos) {
        this.albumid = albumid;
        this.currentNumber = currentNumber;
        this.maxNumber = maxNumber;
        this.photos=photos;
    }

    public AlbumVO() {
    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public PhotoVO[] getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoVO[] photos) {
        this.photos = photos;
    }
}
