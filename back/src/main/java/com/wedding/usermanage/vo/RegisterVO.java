package com.wedding.usermanage.vo;

import java.io.Serializable;

public class RegisterVO implements Serializable {
    String username;
    String password;
    String phone;

    public RegisterVO(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public RegisterVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
