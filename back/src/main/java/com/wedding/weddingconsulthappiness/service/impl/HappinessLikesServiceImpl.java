package com.wedding.weddingconsulthappiness.service.impl;

import com.wedding.mapper.Happiness_likesMapper;
import com.wedding.model.po.Happiness_likes;
import com.wedding.weddingconsulthappiness.service.HappinessLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value="HappinessLikesService")
public class HappinessLikesServiceImpl implements HappinessLikesService {
    @Autowired
    Happiness_likesMapper hlm;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return hlm.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Happiness_likes record) {
        return hlm.insert(record);
    }

    @Override
    public Happiness_likes selectByPrimaryKey(Integer id) {
        return hlm.selectByPrimaryKey(id);
    }

    @Override
    public List<Happiness_likes> selectAll() {
        return hlm.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Happiness_likes record) {
        return hlm.updateByPrimaryKey(record);
    }
}
