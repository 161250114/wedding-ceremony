package com.wedding.weddingconsulthappiness.service;


import com.wedding.model.po.Wedding;

import java.util.List;

public interface WeddingService {
    int deleteByPrimaryKey(Integer id);

    int insert(Wedding record);

    Wedding selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Wedding record);

    List<Wedding> selectAll();
}
