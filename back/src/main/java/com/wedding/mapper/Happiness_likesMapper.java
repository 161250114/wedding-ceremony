package com.wedding.mapper;

import com.wedding.model.po.Happiness_likes;
import java.util.List;

public interface Happiness_likesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Happiness_likes record);

    Happiness_likes selectByPrimaryKey(Integer id);

    List<Happiness_likes> selectAll();

    int updateByPrimaryKey(Happiness_likes record);
}