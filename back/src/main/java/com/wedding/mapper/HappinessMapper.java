package com.wedding.mapper;

import com.wedding.model.po.Happiness;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HappinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Happiness record);

    Happiness selectByPrimaryKey(Integer id);

    List<Happiness> selectAll();

    int updateByPrimaryKey(Happiness record);
}