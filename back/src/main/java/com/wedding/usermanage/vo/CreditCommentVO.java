package com.wedding.usermanage.vo;

import java.io.Serializable;

public class CreditCommentVO implements Serializable {
    int userid1;
    int daterecordid;
    int basiccomment;
    int extracomment;
    int labelcomment;

    public CreditCommentVO(int userid1, int daterecordid, int basiccomment, int extracomment, int labelcomment) {
        this.userid1 = userid1;
        this.daterecordid = daterecordid;
        this.basiccomment = basiccomment;
        this.extracomment = extracomment;
        this.labelcomment = labelcomment;
    }

    public CreditCommentVO() {
    }

    public int getDaterecordid() {
        return daterecordid;
    }

    public void setDaterecordid(int daterecordid) {
        this.daterecordid = daterecordid;
    }

    public int getBasiccomment() {
        return basiccomment;
    }

    public void setBasiccomment(int basiccomment) {
        this.basiccomment = basiccomment;
    }

    public int getExtracomment() {
        return extracomment;
    }

    public void setExtracomment(int extracomment) {
        this.extracomment = extracomment;
    }

    public int getLabelcomment() {
        return labelcomment;
    }

    public void setLabelcomment(int labelcomment) {
        this.labelcomment = labelcomment;
    }

    public int getUserid1() {
        return userid1;
    }

    public void setUserid1(int userid1) {
        this.userid1 = userid1;
    }
}
