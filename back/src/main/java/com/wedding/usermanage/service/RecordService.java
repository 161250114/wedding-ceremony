package com.wedding.usermanage.service;

import com.wedding.usermanage.vo.BillVO;
import com.wedding.usermanage.vo.DateRecordVO;

public interface RecordService {
    BillVO[] getBillRecords(int userid);
    DateRecordVO[] getDateRecords(int userid);
}
