package com.wedding.mapper;

import com.wedding.model.po.Happiness_photo;
import java.util.List;

public interface Happiness_photoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Happiness_photo record);

    Happiness_photo selectByPrimaryKey(Integer id);

    List<Happiness_photo> selectAll();

    int updateByPrimaryKey(Happiness_photo record);
}