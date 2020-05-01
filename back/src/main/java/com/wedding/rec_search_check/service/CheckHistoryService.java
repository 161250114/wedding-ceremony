package com.nju.wedding.service;

import com.github.pagehelper.PageInfo;
import com.nju.wedding.model.CheckHistory;

import java.util.List;

public interface CheckHistoryService {

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public CheckHistory selById(Integer id);

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    public List<CheckHistory> selByUserId(Integer user_id);

    /**
     * 查所有记录
     * @return
     */
    public List<CheckHistory> selAll();

    /**
     * 新增
     * @param checkHistory
     */
    public void add(CheckHistory checkHistory);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param checkHistory
     */
    public void update(CheckHistory checkHistory);

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<CheckHistory> selAll(Integer currPage);

}
