package com.wedding.mapper;

import com.wedding.model.po.UserLabel;
import java.util.List;

public interface UserLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLabel record);

    UserLabel selectByPrimaryKey(Integer id);

    List<UserLabel> selectAll();

    int updateByPrimaryKey(UserLabel record);

<<<<<<< HEAD
    UserLabel selectByUserId(Integer user_id);
=======
    List<UserLabel> selectByUserId(Integer user_id);
>>>>>>> 0c6a6b02f133443f0d976f10236fd737e7cc4ecd

    List<UserLabel> selectByLabel(String label);
}