package com.wedding.usermanage.service.impl;

import com.wedding.mapper.CreditCommentMapper;
import com.wedding.mapper.Date_recordMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.CreditComment;
import com.wedding.model.po.Date_record;
import com.wedding.usermanage.service.UserCreditService;
import com.wedding.usermanage.utils.Statistics;
import com.wedding.usermanage.vo.CreditCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("userCreditService")
public class UserCreditServiceImpl implements UserCreditService {
    @Autowired
    private CreditCommentMapper creditCommentMapper;
    @Autowired
    private Date_recordMapper date_recordMapper;
    @Override
    public ReturnMessage addCreditComment(CreditCommentVO creditCommentVO) {
        CreditComment creditComment=new CreditComment();
        creditComment.setBasiccomment(creditCommentVO.getBasiccomment());
        creditComment.setDaterecordid(creditCommentVO.getDaterecordid());
        creditComment.setExtracomment(creditCommentVO.getExtracomment());
        creditComment.setLabelcomment(creditCommentVO.getLabelcomment());
        creditComment.setUserid1(creditCommentVO.getUserid1());

        Date_record date_record=date_recordMapper.selectByPrimaryKey(creditCommentVO.getDaterecordid());
        if(date_record.getUserid1()==creditCommentVO.getUserid1()){
            creditComment.setUserid2(date_record.getUserid2());
        }else{
            creditComment.setUserid2(date_record.getUserid1());
        }
        creditCommentMapper.insert(creditComment);
        this.getNewTrueness(creditComment.getUserid2());//获取用户新的信息真实度

        return new ReturnMessage(true,"评价成功");
    }


    public int getNewTrueness(int userid){
        //基本公式：weighted rank (WR) = (v ÷ (v+m)) × R + (m ÷ (v+m)) × C
        List<CreditComment> creditComments=creditCommentMapper.selectByUser2(userid);
        int m=5;//假设初始已有5个人进行评分
        int c= Statistics.meanCredit;//c为当前所有用户平均分
        int v=creditComments.size();//v为已评分用户数量
        double vR=0;
        for(int i=0;i<v;i++){
            CreditComment creditComment=new CreditComment();
            double trueness1=creditComment.getBasiccomment()*Statistics.basicCreditRatio
                    +creditComment.getExtracomment()*Statistics.extraCreditRatio
                    +creditComment.getLabelcomment()*Statistics.labelCreditRatio;//三项指标加权平均分
            Date_record date_record=date_recordMapper.selectByPrimaryKey(creditComment.getDaterecordid());
            int days=getDays(date_record.getStartDate(),date_record.getEndDate());
            double daysWeight=getWeightByDays(days);
            vR=vR+trueness1*daysWeight+c*(1-daysWeight);//时间加权平均分
        }
        double result=(vR+m*c)/(v+m);//贝叶斯统计算法
        return (int)result;
    }

    public int getDays(Date datestart,Date dateend){
        int days = (int)((dateend.getTime()-datestart.getTime())/(3600*1000*24));
        return days;
    }

    public static double getWeightByDays(int days)
    {
        if (days==0){
            return 0;
        }else if(days>=30){
            return 1;
        }
        double a=(days-15)/5.0;
        double p = 0.2316419;
        double b1 = 0.31938153;
        double b2 = -0.356563782;
        double b3 = 1.781477937;
        double b4 = -1.821255978;
        double b5 = 1.330274429;

        double x = Math.abs(a);
        double t = 1/(1+p*x);

        double val = 1 - (1/(Math.sqrt(2*Math.PI))  * Math.exp(-1*Math.pow(a, 2)/2)) *
                (b1*t + b2 * Math.pow(t,2) + b3*Math.pow(t,3) + b4 * Math.pow(t,4) + b5 * Math.pow(t,5) );//泰勒展开
        if ( a < 0 )val = 1- val;
        return val;
    }
}
