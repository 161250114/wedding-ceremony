package com.wedding.mapper;

import com.wedding.model.po.Date_record;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Date_recordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Date_record record);

    Date_record selectByPrimaryKey(Integer id);

    List<Date_record> selectAll();

    int updateByPrimaryKey(Date_record record);

    List<Date_record> selectByUserId1AndUserId2(Integer userid);

}
