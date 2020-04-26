package com.wedding.mapper;

import com.wedding.model.po.Date_standard;
import java.util.List;

public interface Date_standardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Date_standard record);

    Date_standard selectByPrimaryKey(Integer id);

    List<Date_standard> selectAll();

    int updateByPrimaryKey(Date_standard record);
}