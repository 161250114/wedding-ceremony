package com.wedding.model.po;

public class Friend_apply {
    private Integer id;

    private Integer userid1;

    private Integer userid2;

    private String applyinfo;

    private String result;

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

    public String getApplyinfo() {
        return applyinfo;
    }

    public void setApplyinfo(String applyinfo) {
        this.applyinfo = applyinfo == null ? null : applyinfo.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}