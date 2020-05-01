package com.wedding.mapper;

import com.wedding.model.po.CheckHistory;
import java.util.List;

public interface CheckHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckHistory record);

    CheckHistory selectByPrimaryKey(Integer id);

    List<CheckHistory> selectAll();

    int updateByPrimaryKey(CheckHistory record);
}