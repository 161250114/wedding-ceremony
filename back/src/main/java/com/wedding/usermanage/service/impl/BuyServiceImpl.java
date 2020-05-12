package com.wedding.usermanage.service.impl;

import com.wedding.mapper.AlbumMapper;
import com.wedding.mapper.BillMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.Album;
import com.wedding.model.po.Bill;
import com.wedding.model.po.User;
import com.wedding.usermanage.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

@Service("buyService")
public class BuyServiceImpl implements BuyService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private AlbumMapper albumMapper;

    static HashMap<Integer, Double> vipPrice = new HashMap<Integer, Double>() {
        {
            put(30, 10.0);
            put(90, 30.0);
            put(365, 108.0);
        }
    };
    static HashMap<Integer, Double> albumPrice = new HashMap<Integer, Double>() {
        {
            put(5, 5.0);
            put(10, 10.0);
            put(20, 15.0);
        }
    };

    @Override
    public ReturnMessage chargeMoney(int userid, double money) {
        User user=userMapper.selectByPrimaryKey(userid);
        user.setBalance(user.getBalance()+money);
        userMapper.updateByPrimaryKey(user);
        Bill bill=new Bill();
        bill.setBalance(user.getBalance());
        bill.setMoney(money);
        bill.setRemark("充值了"+money+"元");
        bill.setType("充值");
        bill.setUserid(user.getId());
        bill.setTime(new Date());
        billMapper.insert(bill);
        return new ReturnMessage(true,"OK");
    }

    @Override
    public ReturnMessage buyVIP(int userid, int vipDays) {
        User user=userMapper.selectByPrimaryKey(userid);
        double cost=vipPrice.get(vipDays);
        if(user.getBalance()<cost){
            return new ReturnMessage(false,"您的余额不足，请充值");
        }else{
            Date date=new   Date();//取时间
            if(user.getVipEnddate()!=null&&date.before(user.getVipEnddate())) {
                date = user.getVipEnddate();
            }
            Calendar calendar   =   new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,vipDays);//把日期往后增加一天.整数往后推,负数往前移动
            date=calendar.getTime();   //这个时间就是日期往后推一天的结果
            user.setVipEnddate(date);
            user.setUsertype((byte)1);
            user.setBalance(user.getBalance()-cost);
            userMapper.updateByPrimaryKey(user);
            //添加账单信息
            Bill bill=new Bill();
            bill.setBalance(user.getBalance());
            bill.setMoney(cost);
            bill.setRemark("购买"+vipDays+"天会员，消费了"+cost+"元");
            bill.setType("购买");
            bill.setUserid(user.getId());
            bill.setTime(new Date());
            billMapper.insert(bill);
            return new ReturnMessage(true,"续费成功");
        }
    }

    @Override
    public ReturnMessage buyAlbumMax(int userid, int num) {
        User user=userMapper.selectByPrimaryKey(userid);
        double cost=albumPrice.get(num);
        if(user.getBalance()<cost){
            return new ReturnMessage(false,"您的余额不足，请充值");
        }else{
            Album album=albumMapper.selectByPrimaryKey(user.getAlbumid());
            album.setMaxNumber(album.getMaxNumber()+num);
            user.setBalance(user.getBalance()-cost);
            albumMapper.updateByPrimaryKey(album);
            userMapper.updateByPrimaryKey(user);
            //添加账单信息
            Bill bill=new Bill();
            bill.setBalance(user.getBalance());
            bill.setMoney(cost);
            bill.setRemark("购买相册最大容量"+num+"张，消费了"+cost+"元");
            bill.setType("购买");
            bill.setUserid(user.getId());
            bill.setTime(new Date());
            billMapper.insert(bill);
            return new ReturnMessage(true,"购买成功");

        }
    }

    @Override
    public ReturnMessage getAlbumMax(int userid) {
        User user=userMapper.selectByPrimaryKey(userid);
        Album album=albumMapper.selectByPrimaryKey(user.getAlbumid());
        return new ReturnMessage(true,album.getMaxNumber());
    }


}
