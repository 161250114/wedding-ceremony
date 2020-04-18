package com.wedding.usermanage.vo;

import java.io.Serializable;

public class RegisterVO implements Serializable {
    String username;
    String password;
    String phone;
    String fullName;
    int sex;
    String birthday;
    int height;
    String introduction;
    String address;
    String education;
    int marriage;
    String salary;
    String profession;

    public RegisterVO(String username, String password, String phone, String fullName, int sex, String birthday, int height, String introduction, String address, String education, int marriage, String salary, String profession) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
        this.sex = sex;
        this.birthday = birthday;
        this.height = height;
        this.introduction = introduction;
        this.address = address;
        this.education = education;
        this.marriage = marriage;
        this.salary = salary;
        this.profession = profession;
    }

    public RegisterVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getMarriage() {
        return marriage;
    }

    public void setMarriage(int marriage) {
        this.marriage = marriage;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
