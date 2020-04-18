package com.wedding.mapper;

import com.wedding.model.po.System_message;
import java.util.List;

public interface System_messageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(System_message record);

    System_message selectByPrimaryKey(Integer id);

    List<System_message> selectAll();

    int updateByPrimaryKey(System_message record);
}