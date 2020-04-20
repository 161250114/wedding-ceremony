package com.wedding.usermanage.vo;

import java.io.Serializable;

public class LoginVO implements Serializable {
    String uname_phone;
    String password;

    public LoginVO(String uname_phone, String password) {
        this.uname_phone = uname_phone;
        this.password = password;
    }

    public LoginVO() {
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
