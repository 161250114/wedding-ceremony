package com.wedding.mapper;

import com.wedding.model.po.CreditComment;
import java.util.List;

public interface CreditCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditComment record);

    CreditComment selectByPrimaryKey(Integer id);

    List<CreditComment> selectAll();

    int updateByPrimaryKey(CreditComment record);

    CreditComment selectByUser1AndDateRecord(int userid1,int daterecordid);

    List<CreditComment> selectByUser2(int userid2);
}
