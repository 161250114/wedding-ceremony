package com.wedding.model.po;

import java.io.Serializable;
import java.util.Date;

public class Wedding_record implements Serializable {
    private Integer id;

    private Integer weddingId;

    private Integer approverId;

    private Date time;

    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Integer weddingId) {
        this.weddingId = weddingId;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}