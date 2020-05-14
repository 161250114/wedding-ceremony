package com.wedding.weddingconsulthappiness.vo;

import com.wedding.model.po.Wedding_record;

import java.text.SimpleDateFormat;

public class WeddingRecordVO {

    private int id;
    private int weddingId;
    private int approverId;
    private String time;
    private String result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(int weddingId) {
        this.weddingId = weddingId;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public WeddingRecordVO() {
    }

    public WeddingRecordVO(int id, int weddingId, int approverId, String time, String result) {
        this.id = id;
        this.weddingId = weddingId;
        this.approverId = approverId;
        this.time = time;
        this.result = result;
    }

    public WeddingRecordVO(Wedding_record w){
        this.id=w.getId();
        this.weddingId=w.getWeddingId();
        this.approverId=w.getApproverId();
        this.result=w.getResult();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.time=df.format(w.getTime());
    }

}
