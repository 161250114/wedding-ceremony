package com.wedding.model.po;

public class User_relation {
    private Integer id;

    private Integer userid1;

    private Integer userid2;

    private String remark;

    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid1() {
        return userid1;
    }

    public void setUserid1(Integer userid1) {
        this.userid1 = userid1;
    }

    public Integer getUserid2() {
        return userid2;
    }

    public void setUserid2(Integer userid2) {
        this.userid2 = userid2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}