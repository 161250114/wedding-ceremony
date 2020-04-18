package com.wedding.weddingconsulthappiness.vo;

import java.util.Date;

public class WeddingVO {
    int id;
    int applicant_id;
    String name;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    String phone;
    String email;

    public WeddingVO(int id, int applicant_id, String name, String phone, String email, int total, Date start, Date end, String location, String detail, String state) {
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

    int total;
    Date start;
    Date end;
    String location;
    String detail;
    String state;
}
