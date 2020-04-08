package com.wedding.mapper;

import com.wedding.model.po.Wedding_record;
import java.util.List;

public interface Wedding_recordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wedding_record record);

    Wedding_record selectByPrimaryKey(Integer id);

    List<Wedding_record> selectAll();

    int updateByPrimaryKey(Wedding_record record);
}