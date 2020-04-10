package com.wedding.mapper;

import com.wedding.model.po.User_relation;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface User_relationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User_relation record);

    User_relation selectByPrimaryKey(Integer id);

    List<User_relation> selectAll();

    int updateByPrimaryKey(User_relation record);
}