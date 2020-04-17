package com.wedding.usermanage.service;


import com.wedding.model.po.Wedding_record;

import java.util.List;

public interface WeddingRecordService {
    int deleteByPrimaryKey(Integer id);

    int insert(Wedding_record record);

    Wedding_record selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Wedding_record record);

    List<Wedding_record> selectAll();
}
