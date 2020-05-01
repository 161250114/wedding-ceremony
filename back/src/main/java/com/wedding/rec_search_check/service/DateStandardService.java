package com.wedding.rec_search_check.service;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.Standard;

import java.util.List;

public interface StandardService {
    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public Standard selById(Integer id);

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    public Standard selByUserId(Integer user_id);

    /**
     * 查所有记录
     * @return
     */
    public List<Standard> selAll();

    /**
     * 新增
     * @param standard
     */
    public void add(Standard standard);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param standard
     */
    public void update(Standard standard);

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<Standard> selAll(Integer currPage);

}
