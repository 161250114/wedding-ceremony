package com.wedding.mapper;

import com.wedding.model.po.User_limit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_limitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User_limit record);

    User_limit selectByPrimaryKey(Integer id);

    List<User_limit> selectAll();

    int updateByPrimaryKey(User_limit record);

    User_limit selectByUserId(Integer userid);

}
