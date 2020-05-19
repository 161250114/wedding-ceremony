package com.wedding.model.po;

public class CreditComment {
    private Integer id;

    private Integer userid;

    private Integer daterecordid;

    private Integer basiccomment;

    private Integer extracomment;

    private Integer labelcomment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getDaterecordid() {
        return daterecordid;
    }

    public void setDaterecordid(Integer daterecordid) {
        this.daterecordid = daterecordid;
    }

    public Integer getBasiccomment() {
        return basiccomment;
    }

    public void setBasiccomment(Integer basiccomment) {
        this.basiccomment = basiccomment;
    }

    public Integer getExtracomment() {
        return extracomment;
    }

    public void setExtracomment(Integer extracomment) {
        this.extracomment = extracomment;
    }

    public Integer getLabelcomment() {
        return labelcomment;
    }

    public void setLabelcomment(Integer labelcomment) {
        this.labelcomment = labelcomment;
    }
}