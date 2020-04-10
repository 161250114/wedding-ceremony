package com.wedding.mapper;

import com.wedding.model.po.Wedding;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WeddingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wedding record);

    Wedding selectByPrimaryKey(Integer id);

    List<Wedding> selectAll();

    int updateByPrimaryKey(Wedding record);
}