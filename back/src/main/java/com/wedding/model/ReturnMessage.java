package com.wedding.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class ReturnMessage {
    private boolean result;
    private Object message;

    public ReturnMessage(boolean result, Object message) {
        this.result = result;
        this.message = message;
    }

    public ReturnMessage() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
