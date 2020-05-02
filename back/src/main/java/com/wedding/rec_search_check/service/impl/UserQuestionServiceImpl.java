package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wedding.mapper.UserQuestionMapper;
import com.wedding.model.po.UserQuestion;
import com.wedding.rec_search_check.service.UserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQuestionServiceImpl implements UserQuestionService {

    @Autowired
    private UserQuestionMapper userQuestionMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public UserQuestion selById(Integer id) {
        return userQuestionMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    @Override
    public List<UserQuestion> selByUserId(Integer user_id) {
        return userQuestionMapper.selectByUserId(user_id);
    }

    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<UserQuestion> selAll() {
        return userQuestionMapper.selectAll();
    }

    /**
     * 新增
     * @param userQuestion
     */
    @Override
    public void add(UserQuestion userQuestion) {
        userQuestionMapper.insert(userQuestion);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        userQuestionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param userQuestion
     */
    @Override
    public void update(UserQuestion userQuestion) {
        userQuestionMapper.updateByPrimaryKey(userQuestion);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<UserQuestion> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<UserQuestion> pageInfo = new PageInfo<>(userQuestionMapper.selectAll());
        return pageInfo;
    }
}
