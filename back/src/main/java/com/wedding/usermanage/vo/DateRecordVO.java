package com.wedding.usermanage.vo;

import java.io.Serializable;
import java.util.Date;

public class DateRecordVO implements Serializable {
    private Integer userid2;

    private String startDate;

    private String endDate;

    public DateRecordVO(Integer userid2, String startDate, String endDate) {
        this.userid2 = userid2;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DateRecordVO() {
    }

    public Integer getUserid2() {
        return userid2;
    }

    public void setUserid2(Integer userid2) {
        this.userid2 = userid2;
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
}
