package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.System_messageMapper;
import com.wedding.model.po.System_message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="SystemMessageService")
public class SystemMessageServiceImpl implements SystemMessageService {
    @Autowired
    private System_messageMapper smm;//这里会报错，但是并不会影响
    @Override
    public int addSystemMessage(System_message sm) {
        return smm.insert(sm);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return smm.deleteByPrimaryKey(id);
    }

    @Override
    public System_message selectByPrimaryKey(Integer id) {
        return smm.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(System_message record) {
        return smm.updateByPrimaryKey(record);
    }

    @Override
    public List<System_message> selectAll() {
        return smm.selectAll();
    }

    @Override
    public int getId() {
        return smm.selectAll().size();
    }
}
