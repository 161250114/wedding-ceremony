package com.wedding.mapper;

import com.wedding.model.po.ChatHistory;
import java.util.List;

public interface ChatHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChatHistory record);

    ChatHistory selectByPrimaryKey(Integer id);

    List<ChatHistory> selectByUserId(Integer user_id);

    List<ChatHistory> selectByChattedUserId(Integer chatted_user_id);

    List<ChatHistory> selectAll();

    int updateByPrimaryKey(ChatHistory record);
}