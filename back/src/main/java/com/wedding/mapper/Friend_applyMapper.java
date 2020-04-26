package com.wedding.mapper;

import com.wedding.model.po.Friend_apply;
import java.util.List;

public interface Friend_applyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend_apply record);

    Friend_apply selectByPrimaryKey(Integer id);

    List<Friend_apply> selectAll();

    int updateByPrimaryKey(Friend_apply record);
}