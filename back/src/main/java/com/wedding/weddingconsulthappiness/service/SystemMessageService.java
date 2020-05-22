package com.wedding.weddingconsulthappiness.service;


import com.wedding.model.po.System_message;
import com.wedding.weddingconsulthappiness.vo.MessageState;

import java.util.List;

public interface SystemMessageService {
    int addSystemMessage(System_message sm);

     List<System_message> getsm(Integer number);

    public int read(String str);

    public List<System_message> getAll();

    public List<MessageState> getState();

    public int getMyState(int id);
}
