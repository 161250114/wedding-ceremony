package com.wedding.mapper;

import com.wedding.model.po.CreditComment;
import java.util.List;

public interface CreditCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditComment record);

    CreditComment selectByPrimaryKey(Integer id);

    List<CreditComment> selectAll();

    int updateByPrimaryKey(CreditComment record);
}