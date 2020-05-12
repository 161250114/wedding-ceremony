package com.wedding.usermanage.vo;

import java.io.Serializable;

public class FriendApplyVO implements Serializable {
    int id;
    int userid1;
    int userid2;
    String applyInfo;
    String result;
    String replyInfo;
    String applyDate;

    public FriendApplyVO(int id,int userid1, int userid2, String applyInfo, String result, String replyInfo, String applyDate) {
        this.id=id;
        this.userid1 = userid1;
        this.userid2 = userid2;
        this.applyInfo = applyInfo;
        this.result = result;
        this.replyInfo = replyInfo;
        this.applyDate = applyDate;
    }

    public FriendApplyVO() {
    }

    public int getUserid2() {
        return userid2;
    }

    public void setUserid2(int userid2) {
        this.userid2 = userid2;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    public int getUserid1() {
        return userid1;
    }

    public void setUserid1(int userid1) {
        this.userid1 = userid1;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(String replyInfo) {
        this.replyInfo = replyInfo;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
