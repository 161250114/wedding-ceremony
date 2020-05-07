package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;

public interface BuyService {
    ReturnMessage chargeMoney(int userid,double money);
    ReturnMessage buyVIP(int userid,int vipDays);
    ReturnMessage buyAlbumMax(int userid,int num);
}
