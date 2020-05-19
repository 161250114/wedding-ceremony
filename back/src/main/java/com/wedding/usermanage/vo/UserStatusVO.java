package com.wedding.usermanage.vo;

import java.io.Serializable;

public class UserStatusVO implements Serializable {
    int userid;
    String username;
    int usertype;
    int dateStatus;
    int dateFriendId;
    double balance;
    String vipEnddate;
    String phone;
    String headPhotoUrl;
    int newFapplyNum;
    int newDapplyNum;

    public UserStatusVO(int userid, String username, int usertype, int dateStatus, int dateFriendId, double balance, String vipEnddate, String phone, String headPhotoUrl, int newFapplyNum, int newDapplyNum) {
        this.userid = userid;
        this.username = username;
        this.usertype = usertype;
        this.dateStatus = dateStatus;
        this.dateFriendId = dateFriendId;
        this.balance = balance;
        this.vipEnddate = vipEnddate;
        this.phone = phone;
        this.headPhotoUrl = headPhotoUrl;
        this.newFapplyNum = newFapplyNum;
        this.newDapplyNum = newDapplyNum;
    }

    public UserStatusVO() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public int getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(int dateStatus) {
        this.dateStatus = dateStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getVipEnddate() {
        return vipEnddate;
    }

    public void setVipEnddate(String vipEnddate) {
        this.vipEnddate = vipEnddate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPhotoUrl() {
        return headPhotoUrl;
    }

    public void setHeadPhotoUrl(String headPhotoUrl) {
        this.headPhotoUrl = headPhotoUrl;
    }

    public int getDateFriendId() {
        return dateFriendId;
    }

    public void setDateFriendId(int dateFriendId) {
        this.dateFriendId = dateFriendId;
    }

    public int getNewFapplyNum() {
        return newFapplyNum;
    }

    public void setNewFapplyNum(int newFapplyNum) {
        this.newFapplyNum = newFapplyNum;
    }

    public int getNewDapplyNum() {
        return newDapplyNum;
    }

    public void setNewDapplyNum(int newDapplyNum) {
        this.newDapplyNum = newDapplyNum;
    }
}
