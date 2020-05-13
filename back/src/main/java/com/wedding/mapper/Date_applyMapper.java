package com.wedding.mapper;

import com.wedding.model.po.Date_apply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Date_applyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Date_apply record);

    Date_apply selectByPrimaryKey(Integer id);

    List<Date_apply> selectAll();

    int updateByPrimaryKey(Date_apply record);

    List<Date_apply> selectByUserid1(int userid1);

    List<Date_apply> selectByUserid2(int userid2);

}
