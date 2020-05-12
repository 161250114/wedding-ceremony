package com.wedding.usermanage.vo;

import java.io.Serializable;

public class FriendVO implements Serializable {
    int userid;
    String username;
    String remark;
    int type;

    public FriendVO(int userid, String remark, int type,String username) {
        this.userid = userid;
        this.remark = remark;
        this.type = type;
        this.username=username;
    }

    public FriendVO() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
