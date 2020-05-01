package com.nju.wedding.service;

import com.github.pagehelper.PageInfo;
import com.nju.wedding.model.ChatHistory;
import java.util.List;

public interface ChatHistoryService {

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public ChatHistory selById(Integer id);

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    public ChatHistory selByUserId(Integer user_id);

    /**
     * 查所有记录
     * @return
     */
    public List<ChatHistory> selAll();

    /**
     * 新增
     * @param chatHistory
     */
    public void add(ChatHistory chatHistory);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param chatHistory
     */
    public void update(ChatHistory chatHistory);

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<ChatHistory> selAll(Integer currPage);

}
