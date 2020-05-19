package com.wedding.usermanage.service.impl;

import com.wedding.mapper.BillMapper;
import com.wedding.mapper.Date_recordMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.Bill;
import com.wedding.model.po.Date_record;
import com.wedding.model.po.User;
import com.wedding.usermanage.service.RecordService;
import com.wedding.usermanage.vo.BillVO;
import com.wedding.usermanage.vo.DateRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private BillMapper billMapper;
    @Autowired
    private Date_recordMapper date_recordMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CreditCommentMapper creditCommentMapper;

    @Override
    public BillVO[] getBillRecords(int userid) {
        List<Bill> billList=billMapper.selectByUserId(userid);
        BillVO[] billVOS=new BillVO[billList.size()];
        for(int i=0;i<billList.size();i++){
            BillVO billVO=new BillVO();
            Bill bill=billList.get(i);
            billVO.setBalance(bill.getBalance());
            billVO.setMoney(bill.getMoney());
            billVO.setRemark(bill.getRemark());
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            billVO.setTime(format.format(bill.getTime()));
            billVO.setType(bill.getType());
            billVOS[i]=billVO;
        }
        return billVOS;
    }

    @Override
    public DateRecordVO[] getDateRecords(int userid) {
        List<Date_record> date_records=date_recordMapper.selectByUserId1AndUserId2(userid);
        DateRecordVO[] dateRecordVOS=new DateRecordVO[date_records.size()];
        for(int i=0;i<date_records.size();i++){
            DateRecordVO dateRecordVO=new DateRecordVO();
            Date_record date_record=date_records.get(i);
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            dateRecordVO.setStatus("在进行");
            if(date_record.getEndDate()!=null){
                dateRecordVO.setEndDate(dateFormat.format(date_record.getEndDate()));
                dateRecordVO.setStatus("有缘无分");
            }
            dateRecordVO.setStartDate(dateFormat.format(date_record.getStartDate()));
            if(date_record.getUserid1()==userid){
                dateRecordVO.setUserid2(date_record.getUserid2());
            }else{
                dateRecordVO.setUserid2(date_record.getUserid1());
            }
            dateRecordVO.setUserid1(userid);
            dateRecordVO.setId(date_record.getId());
            dateRecordVOS[i]=dateRecordVO;
        }
        return dateRecordVOS;
    }

    @Override
    public ReturnMessage stopDating(DateRecordVO dateRecordVO) {
        Date_record date_record=date_recordMapper.selectByPrimaryKey(dateRecordVO.getId());
        date_record.setEndDate(new Date());
        date_recordMapper.updateByPrimaryKey(date_record);

        User user1=userMapper.selectByPrimaryKey(date_record.getUserid1());
        user1.setDateStatus((byte)0);
        userMapper.updateByPrimaryKey(user1);

        User user2=userMapper.selectByPrimaryKey(date_record.getUserid2());
        user2.setDateStatus((byte)0);
        userMapper.updateByPrimaryKey(user2);
        return new ReturnMessage(true,"约会已结束！");
    }
}
