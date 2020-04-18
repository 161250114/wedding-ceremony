package com.wedding.mapper;

import com.wedding.model.po.Wedding;
import java.util.List;

public interface WeddingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wedding record);

    Wedding selectByPrimaryKey(Integer id);

    List<Wedding> selectAll();

    int updateByPrimaryKey(Wedding record);
}