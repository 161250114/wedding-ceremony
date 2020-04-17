package com.wedding.usermanage.service;

import com.wedding.model.po.System_message;

import java.util.List;

public interface SystemMessageService {
    int addSystemMessage(System_message sm);

    int deleteByPrimaryKey(Integer id);

    System_message selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(System_message record);

    List<System_message> selectAll();

    int getId();
}
