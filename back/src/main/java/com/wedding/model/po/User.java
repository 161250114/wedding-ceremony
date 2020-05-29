package com.wedding.model.po;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private Byte usertype;//0为免费用户，1为付费用户，2为管理员

    private String fullname;

    private Byte sex;

    private Date birthday;

    private Integer height;

    private String introduction;

    private String address;

    private String education;

    private Byte marrige;

    private String salary;

    private Double balance;

    private Integer trueness;

    private String profession;

    private Integer albumid;

    private Date vipEnddate;

    private Byte dateStatus;//0为暂无约会对象，1为有约会对象

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Byte getUsertype() {
        return usertype;
    }

    public void setUsertype(Byte usertype) {
        this.usertype = usertype;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public Byte getMarrige() {
        return marrige;
    }

    public void setMarrige(Byte marrige) {
        this.marrige = marrige;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getTrueness() {
        return trueness;
    }

    public void setTrueness(Integer trueness) {
        this.trueness = trueness;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public Integer getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    public Date getVipEnddate() {
        return vipEnddate;
    }

    public void setVipEnddate(Date vipEnddate) {
        this.vipEnddate = vipEnddate;
    }

    public Byte getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(Byte dateStatus) {
        this.dateStatus = dateStatus;
    }
}
