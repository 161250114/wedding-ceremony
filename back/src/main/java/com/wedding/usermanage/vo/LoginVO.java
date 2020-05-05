package com.wedding.usermanage.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class LoginVO implements Serializable {
    int userid;
    String uname_phone;
    String password;

    public LoginVO(int userid, String uname_phone, String password) {
        this.userid = userid;
        this.uname_phone = uname_phone;
        this.password = password;
    }

    public LoginVO() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUname_phone() {
        return uname_phone;
    }

    public void setUname_phone(String uname_phone) {
        this.uname_phone = uname_phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
