package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.WeddingMapper;
import com.wedding.model.po.Wedding;
import com.wedding.usermanage.service.WeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="WeddingService")
public class WeddingServiceImpl implements WeddingService {
    @Autowired
    WeddingMapper wm;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wm.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Wedding record) {
        return wm.insert(record);
    }

    @Override
    public Wedding selectByPrimaryKey(Integer id) {
        return wm.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(Wedding record) {
        return wm.updateByPrimaryKey(record);
    }

    @Override
    public List<Wedding> selectAll() {
        return wm.selectAll();
    }
}
