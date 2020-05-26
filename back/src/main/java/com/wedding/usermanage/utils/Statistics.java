package com.wedding.usermanage.utils;

import com.wedding.mapper.UserMapper;
import com.wedding.model.po.User;
import com.wedding.usermanage.service.UserCreditService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Statistics {
    public static int meanCredit=60;
    public static double basicCreditRatio=0.3;
    public static double extraCreditRatio=0.4;
    public static double labelCreditRatio=0.3;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCreditService userCreditService;
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void updateUserTrueness() {
        List<User> users=userMapper.selectAll();
        int sum=0;
        for(int i=0;i<users.size();i++){
            sum=sum+users.get(i).getTrueness();
        }
        if((Math.abs(sum/users.size())-this.meanCredit)<1){
            this.meanCredit=sum/users.size();
            for (int i=0;i<users.size();i++){
                users.get(i).setTrueness(userCreditService.getNewTrueness(users.get(i).getId()));
                userMapper.updateByPrimaryKey(users.get(i));
            }
        }
    }
}
