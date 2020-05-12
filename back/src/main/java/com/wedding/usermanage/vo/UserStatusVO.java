package com.wedding.usermanage.vo;

import java.io.Serializable;

public class UserStatusVO implements Serializable {
    int userid;
    String username;
    int usertype;
    int dateStatus;
    double balance;
    String vipEnddate;
    String phone;


    public UserStatusVO(int userid, String username, int usertype, int dateStatus, double balance,String vipEnddate,String phone) {
        this.userid = userid;
        this.username = username;
        this.usertype = usertype;
        this.dateStatus = dateStatus;
        this.balance = balance;
        this.vipEnddate=vipEnddate;
        this.phone=phone;
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
}
