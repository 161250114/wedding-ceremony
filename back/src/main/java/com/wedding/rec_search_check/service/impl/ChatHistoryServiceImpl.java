package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wedding.mapper.ChatHistoryMapper;
import com.wedding.model.po.ChatHistory;
import com.wedding.rec_search_check.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    @Autowired
    private ChatHistoryMapper chatHistoryMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public ChatHistory selById(Integer id) {
        return chatHistoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    @Override
    public List<ChatHistory> selByUserId(Integer user_id) {
        return chatHistoryMapper.selectByUserId(user_id);
    }

    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<ChatHistory> selAll() {
        return chatHistoryMapper.selectAll();
    }

    /**
     * 新增
     * @param chatHistory
     */
    @Override
    public void add(ChatHistory chatHistory) {
        chatHistoryMapper.insert(chatHistory);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        chatHistoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param chatHistory
     */
    @Override
    public void update(ChatHistory chatHistory) {
        chatHistoryMapper.updateByPrimaryKey(chatHistory);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<ChatHistory> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<ChatHistory> pageInfo = new PageInfo<>(chatHistoryMapper.selectAll());
        return pageInfo;
    }
}
