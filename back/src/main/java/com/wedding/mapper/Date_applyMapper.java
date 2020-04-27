package com.wedding.mapper;

import com.wedding.model.po.Date_apply;
import java.util.List;

public interface Date_applyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Date_apply record);

    Date_apply selectByPrimaryKey(Integer id);

    List<Date_apply> selectAll();

    int updateByPrimaryKey(Date_apply record);
}