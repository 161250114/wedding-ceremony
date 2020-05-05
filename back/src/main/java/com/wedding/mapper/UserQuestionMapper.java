package com.wedding.mapper;

import com.wedding.model.po.UserQuestion;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserQuestion record);

    UserQuestion selectByPrimaryKey(Integer id);

    List<UserQuestion> selectAll();

    int updateByPrimaryKey(UserQuestion record);

    List<UserQuestion> selectByUserId(Integer user_id);
}