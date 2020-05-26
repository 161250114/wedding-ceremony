package com.wedding.weddingconsulthappiness.service;


import com.wedding.model.po.Wedding_record;
import com.wedding.weddingconsulthappiness.vo.WeddingRecordVO;

import java.util.List;

public interface WeddingRecordService {

    public int add( Wedding_record wr);
    public List<WeddingRecordVO> getAll();
    public int update(WeddingRecordVO vo);

}
