package com.wedding.usermanage.vo;

import java.io.Serializable;

public class ChangePhoneVO implements Serializable {
    String validateKey;
    String newPhone;

    public ChangePhoneVO(String validateKey, String newPhone) {
        this.validateKey = validateKey;
        this.newPhone = newPhone;
    }

    public ChangePhoneVO() {
    }

    public String getValidateKey() {
        return validateKey;
    }

    public void setValidateKey(String validateKey) {
        this.validateKey = validateKey;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }
}
