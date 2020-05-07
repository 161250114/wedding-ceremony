package com.wedding.usermanage.vo;

import java.io.Serializable;

public class FriendVO implements Serializable {
    int userid2;
    String remark;
    int type;

    public FriendVO(int userid2, String remark, int type) {
        this.userid2 = userid2;
        this.remark = remark;
        this.type = type;
    }

    public FriendVO() {
    }

    public int getUserid2() {
        return userid2;
    }

    public void setUserid2(int userid2) {
        this.userid2 = userid2;
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
}
