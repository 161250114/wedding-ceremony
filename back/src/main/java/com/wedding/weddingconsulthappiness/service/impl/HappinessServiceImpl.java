package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.HappinessMapper;
import com.wedding.model.po.Happiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value="HappinessService")
public class HappinessServiceImpl implements HappinessService {
    @Autowired
    HappinessMapper hm;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return hm.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Happiness record) {
        return hm.insert(record);
    }

    @Override
    public Happiness selectByPrimaryKey(Integer id) {
        return hm.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(Happiness record) {
        return hm.updateByPrimaryKey(record);
    }

    @Override
    public List<Happiness> selectAll() {
        return hm.selectAll();
    }
}
