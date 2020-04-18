package com.wedding.mapper;

import com.wedding.model.po.Happiness;
import java.util.List;

public interface HappinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Happiness record);

    Happiness selectByPrimaryKey(Integer id);

    List<Happiness> selectAll();

    int updateByPrimaryKey(Happiness record);
}