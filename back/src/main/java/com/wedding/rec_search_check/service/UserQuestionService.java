package com.wedding.rec_search_check.service;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.UserQuestion;

import java.util.List;

public interface UserQuestionService {

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public UserQuestion selById(Integer id);

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    public List<UserQuestion> selByUserId(Integer user_id);

    /**
     * 查所有记录
     * @return
     */
    public List<UserQuestion> selAll();

    /**
     * 新增
     * @param userQuestion
     */
    public void add(UserQuestion userQuestion);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param userQuestion
     */
    public void update(UserQuestion userQuestion);

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<UserQuestion> selAll(Integer currPage);

}
