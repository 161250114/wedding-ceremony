package com.wedding.rec_search_check.service;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.UserLabel;

import java.util.List;

public interface UserLabelService {

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public UserLabel selById(Integer id);

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    public UserLabel selByUserId(Integer user_id);

    /**
     * 根据标签加载符合条件的用户
     * @param label
     * @return
     */
    public List<UserLabel> selByLabel(String label);

    /**
     * 查所有记录
     * @return
     */
    public List<UserLabel> selAll();

    /**
     * 新增
     * @param userLabel
     */
    public void add(UserLabel userLabel);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param userLabel
     */
    public void update(UserLabel userLabel);

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<UserLabel> selAll(Integer currPage);

}
