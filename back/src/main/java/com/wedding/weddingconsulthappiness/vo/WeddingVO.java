package com.wedding.weddingconsulthappiness.vo;

import com.wedding.model.po.Wedding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeddingVO {
    int id;
    int applicant_id;
    String name;
    String phone;
    String email;
    int total;
    String start;
    String end;
    String location;
    String detail;
    String state;
    public WeddingVO(Wedding w){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.id=w.getId();
        this.applicant_id=w.getApplicantId();
        this.name=w.getName();
        this.phone=w.getPhone();
        this.email=w.getEmail();
        this.total=w.getTotal();
        this.end=df.format(w.getEnd());
        this.start=df.format(w.getStart());
        this.location=w.getLocation();
        this.detail=w.getDetail();
        if(w.getState()==0){
            state="申请中";
        }
        else if(w.getState()==1){
            this.state="通过";
        }
        else{
            this.state="取消";
        }
    }
    public WeddingVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(int applicant_id) {
        this.applicant_id = applicant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public String getLocation() {
        return location;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public WeddingVO(int id, int applicant_id, String name, String phone, String email, int total, String start, String end, String location, String detail, String state) {
        this.id = id;
        this.applicant_id = applicant_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.total = total;
        this.start = start;
        this.end = end;
        this.location = location;
        this.detail = detail;
        this.state = state;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
