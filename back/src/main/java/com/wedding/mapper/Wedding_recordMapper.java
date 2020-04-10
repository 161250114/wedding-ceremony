package com.wedding.mapper;

import com.wedding.model.po.Wedding_record;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Wedding_recordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wedding_record record);

    Wedding_record selectByPrimaryKey(Integer id);

    List<Wedding_record> selectAll();

    int updateByPrimaryKey(Wedding_record record);
}