package com.wedding.mapper;

import com.wedding.model.po.UserLabel;
import java.util.List;

public interface UserLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLabel record);

    UserLabel selectByPrimaryKey(Integer id);

    List<UserLabel> selectAll();

    int updateByPrimaryKey(UserLabel record);
}