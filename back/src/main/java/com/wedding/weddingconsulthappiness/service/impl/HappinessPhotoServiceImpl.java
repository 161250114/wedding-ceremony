package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.Happiness_photoMapper;
import com.wedding.model.po.Happiness_photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value="/HappinessPhotoService")
public class HappinessPhotoServiceImpl implements HappinessPhotoService {
    @Autowired
    Happiness_photoMapper hpm;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return hpm.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Happiness_photo record) {
        return hpm.insert(record);
    }

    @Override
    public Happiness_photo selectByPrimaryKey(Integer id) {
        return hpm.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(Happiness_photo record) {
        return hpm.updateByPrimaryKey(record);
    }

    @Override
    public List<Happiness_photo> selectAll() {
        return hpm.selectAll();
    }
}
