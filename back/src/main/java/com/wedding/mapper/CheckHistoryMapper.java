package com.wedding.mapper;

import com.wedding.model.po.CheckHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckHistory record);

    CheckHistory selectByPrimaryKey(Integer id);

    List<CheckHistory> selectAll();

    int updateByPrimaryKey(CheckHistory record);

    List<CheckHistory> selectByUserId(Integer user_id);
}