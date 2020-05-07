package com.wedding.mapper;

import com.wedding.model.po.UserLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLabel record);

    UserLabel selectByPrimaryKey(Integer id);

    List<UserLabel> selectAll();

    int updateByPrimaryKey(UserLabel record);

    List<UserLabel> selectByUserId(Integer user_id);

    List<UserLabel> selectByLabel(String label);

    int deleteByUserId(Integer user_id);

}
