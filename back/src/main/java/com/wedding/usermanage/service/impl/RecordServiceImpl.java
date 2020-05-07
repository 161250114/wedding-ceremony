package com.wedding.usermanage.service.impl;

import com.wedding.mapper.BillMapper;
import com.wedding.mapper.Date_recordMapper;
import com.wedding.model.po.Bill;
import com.wedding.model.po.Date_record;
import com.wedding.usermanage.service.RecordService;
import com.wedding.usermanage.vo.BillVO;
import com.wedding.usermanage.vo.DateRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private BillMapper billMapper;
    @Autowired
    private Date_recordMapper date_recordMapper;

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
            DateFormat format=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            billVO.setTime(format.format(bill.getTime()));
            billVO.setType(bill.getType());
            billVOS[i]=billVO;
        }
        return billVOS;
    }

    @Override
    public DateRecordVO[] getDateRecords(int userid) {
        List<Date_record> date_records=date_recordMapper.selectByUserId(userid);
        DateRecordVO[] dateRecordVOS=new DateRecordVO[date_records.size()];
        for(int i=0;i<date_records.size();i++){
            DateRecordVO dateRecordVO=new DateRecordVO();
            Date_record date_record=date_records.get(i);
            DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
            dateRecordVO.setEndDate(dateFormat.format(date_record.getEndDate()));
            dateRecordVO.setStartDate(dateFormat.format(date_record.getStartDate()));
            dateRecordVO.setUserid2(date_record.getUserid2());
            dateRecordVOS[i]=dateRecordVO;
        }
        return dateRecordVOS;
    }
}
