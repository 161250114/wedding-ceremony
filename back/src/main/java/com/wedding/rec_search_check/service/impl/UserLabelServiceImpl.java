package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.wedding.mapper.UserLabelMapper;
import com.wedding.model.po.UserLabel;
import com.wedding.rec_search_check.service.UserLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLabelServiceImpl implements UserLabelService {

    @Autowired
    private UserLabelMapper userLabelMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public UserLabel selById(Integer id) {
        return userLabelMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    @Override
    public UserLabel selByUserId(Integer user_id) {
        return userLabelMapper.selectByUserId(user_id);
    }

    /**
     * 根据标签加载符合条件的用户
     * @param label
     * @return
     */
    @Override
    public List<UserLabel> selByLabel(String label) {
        return userLabelMapper.selectByLabel(label);
    }

    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<UserLabel> selAll() {
        return userLabelMapper.selectAll();
    }

    /**
     * 新增
     * @param userLabel
     */
    @Override
    public void add(UserLabel userLabel) {
        userLabelMapper.insert(userLabel);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        userLabelMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param userLabel
     */
    @Override
    public void update(UserLabel userLabel) {
        userLabelMapper.updateByPrimaryKey(userLabel);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<UserLabel> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<UserLabel> pageInfo = new PageInfo<>(userLabelMapper.selectAll());
        return pageInfo;
    }
}
