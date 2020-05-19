package com.wedding.usermanage.vo;

import java.io.Serializable;

public class PhotoVO implements Serializable {
    int id;
    String url;
    int order_number;

    public PhotoVO(int id,String url, int order_number) {
        this.id=id;
        this.url = url;
        this.order_number = order_number;
    }

    public PhotoVO() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
