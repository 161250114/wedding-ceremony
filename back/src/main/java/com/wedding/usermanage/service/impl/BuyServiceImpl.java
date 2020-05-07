package com.wedding.usermanage.service.impl;

import com.wedding.mapper.BillMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.Bill;
import com.wedding.model.po.User;
import com.wedding.usermanage.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("buyService")
public class BuyServiceImpl implements BuyService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BillMapper billMapper;

    @Override
    public ReturnMessage chargeMoney(int userid, double money) {
        User user=userMapper.selectByPrimaryKey(userid);
        user.setBalance(user.getBalance()+money);
        userMapper.updateByPrimaryKey(user);
        Bill bill=new Bill();
        bill.setBalance(user.getBalance());
        bill.setMoney(money);
        bill.setRemark("充值了"+money+"元");
        return new ReturnMessage(true,"OK");
    }

    @Override
    public ReturnMessage buyVIP(int userid, int vipDays) {

        return null;
    }

    @Override
    public ReturnMessage buyAlbumMax(int userid, int num) {
        return null;
    }
}
