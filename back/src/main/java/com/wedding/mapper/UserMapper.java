package com.wedding.mapper;

import com.wedding.model.po.Date_standard;
import com.wedding.model.po.Search;
import com.wedding.model.po.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    User selectByPhone(String phone);

    List<User> selectByLabel(String label);

    List<User> selectByDetail(Search search);

    List<User> selectByStandard(Date_standard date_standard);
}
