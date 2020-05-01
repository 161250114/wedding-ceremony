package com.wedding.mapper;

import com.wedding.model.po.ChatHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChatHistory record);

    ChatHistory selectByPrimaryKey(Integer id);

    List<ChatHistory> selectAll();

    int updateByPrimaryKey(ChatHistory record);

    List<ChatHistory> selectByUserId(Integer user_id);
}