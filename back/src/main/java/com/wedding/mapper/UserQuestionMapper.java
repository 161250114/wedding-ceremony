package com.wedding.mapper;

import com.wedding.model.po.UserQuestion;
import java.util.List;

public interface UserQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserQuestion record);

    UserQuestion selectByPrimaryKey(Integer id);

    List<UserQuestion> selectAll();

    int updateByPrimaryKey(UserQuestion record);
}