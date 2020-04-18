package com.wedding.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class ReturnMessage {
    @JSONField
    private boolean result;
    @JSONField
    private String message;

    public ReturnMessage(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ReturnMessage() {
    }

    public boolean isOK() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
