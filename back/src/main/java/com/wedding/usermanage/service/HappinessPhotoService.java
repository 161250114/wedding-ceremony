package com.wedding.usermanage.service;

import com.wedding.model.po.Happiness_photo;

import java.util.List;

public interface HappinessPhotoService {
    int deleteByPrimaryKey(Integer id);

    int insert(Happiness_photo record);

    Happiness_photo selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Happiness_photo record);

    List<Happiness_photo> selectAll();
}
