package com.wedding.model.po;

import java.util.Date;

public class Wedding {
    private Integer id;

    private Integer applicantId;

    private String name;

    private String phone;

    private String email;

    private Integer total;

    private Date start;

    private Date end;

    private String location;

    private String detail;

    public Wedding() {
    }

    private Integer state;

    public Wedding(Integer id, Integer applicantId, String name, String phone, String email, Integer total, Date start, Date end, String location, String detail, Integer state) {
        this.id = id;
        this.applicantId = applicantId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}