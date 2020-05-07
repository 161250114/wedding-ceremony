package com.wedding.usermanage.vo;

import java.io.Serializable;
import java.util.Date;

public class BillVO implements Serializable {
    private String type;

    private Double money;

    private String remark;

    private String time;

    private Double balance;

    public BillVO(String type, Double money, String remark, String time, Double balance) {
        this.type = type;
        this.money = money;
        this.remark = remark;
        this.time = time;
        this.balance = balance;
    }

    public BillVO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
