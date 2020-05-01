package com.wedding.rec_search_check.service;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.Date_standard;

import java.util.List;

public interface DateStandardService {
    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public Date_standard selById(Integer id);

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    public Date_standard selByUserId(Integer user_id);

    /**
     * 查所有记录
     * @return
     */
    public List<Date_standard> selAll();

    /**
     * 新增
     * @param date_standard
     */
    public void add(Date_standard date_standard);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param date_standard
     */
    public void update(Date_standard date_standard);

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<Date_standard> selAll(Integer currPage);

}
