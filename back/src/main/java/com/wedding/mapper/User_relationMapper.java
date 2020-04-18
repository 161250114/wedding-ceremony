package com.wedding.mapper;

import com.wedding.model.po.User_relation;
import java.util.List;


public interface User_relationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User_relation record);

    User_relation selectByPrimaryKey(Integer id);

    List<User_relation> selectAll();

    int updateByPrimaryKey(User_relation record);
}
