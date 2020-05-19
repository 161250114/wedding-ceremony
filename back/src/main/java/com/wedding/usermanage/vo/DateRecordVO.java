package com.wedding.usermanage.vo;

import java.io.Serializable;
import java.util.Date;

public class DateRecordVO implements Serializable {

    private int id;
    private int userid1;
    private int userid2;

    private String startDate;

    private String endDate;

    private String status;

    private int isCommented;

    public DateRecordVO(int id, int userid1, int userid2, String startDate, String endDate, String status, int isCommented) {
        this.id = id;
        this.userid1 = userid1;
        this.userid2 = userid2;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.isCommented = isCommented;
    }

    public DateRecordVO() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserid1() {
        return userid1;
    }

    public void setUserid1(int userid1) {
        this.userid1 = userid1;
    }

    public void setUserid2(int userid2) {
        this.userid2 = userid2;
    }

    public int getUserid2() {
        return userid2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsCommented() {
        return isCommented;
    }

    public void setIsCommented(int isCommented) {
        this.isCommented = isCommented;
    }
}
