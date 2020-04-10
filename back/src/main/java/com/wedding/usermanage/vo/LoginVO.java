package com.wedding.usermanage.vo;

import java.io.Serializable;

public class LoginVO implements Serializable {
    String username;
    String password;

    public LoginVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginVO() {
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
}
