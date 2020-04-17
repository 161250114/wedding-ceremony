package com.wedding.usermanage.service;

import com.wedding.model.po.Happiness;

import java.util.List;

public interface HappinessService {
    int deleteByPrimaryKey(Integer id);

    int insert(Happiness record);

    Happiness selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Happiness record);

    List<Happiness> selectAll();
}
