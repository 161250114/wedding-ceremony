package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.Wedding_recordMapper;
import com.wedding.model.po.Wedding_record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="WeddingRecordService")
public class WeddingRecordServiceImpl implements WeddingRecordService {
    @Autowired
    Wedding_recordMapper wrp;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wrp.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Wedding_record record) {
        return wrp.insert(record);
    }

    @Override
    public Wedding_record selectByPrimaryKey(Integer id) {
        return wrp.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(Wedding_record record) {
        return wrp.updateByPrimaryKey(record);
    }

    @Override
    public List<Wedding_record> selectAll() {
        return wrp.selectAll();
    }
}
