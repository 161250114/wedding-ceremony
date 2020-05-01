package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wedding.mapper.UserMapper;
import com.wedding.model.po.Date_standard;
import com.wedding.model.po.Search;
import com.wedding.model.po.User;
import com.wedding.rec_search_check.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public User selById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<User> selAll() {
        return userMapper.selectAll();
    }

    /**
     * 按标签查询
     * @return
     */
    @Override
    public List<User> selByLabel(String label) {
        return userMapper.selectByLabel(label);
    }

    /**
     * 详细查询
     * @return
     */
    @Override
    public List<User> selByDetail(Search search) {
        return userMapper.selectByDetail(search);
    }

    /**
     * 根据择偶标准查询
     * @return
     */
    @Override
    public List<User> selByStandard(Date_standard date_standard) {
        return userMapper.selectByStandard(date_standard);
    }

    /**
     * 新增
     * @param user
     */
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param user
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<User> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectAll());
        return pageInfo;
    }

    /**
     * 对按标签搜索后的结果进行分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<User> selLabel(Integer currPage, String label) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByLabel(label));
        return pageInfo;
    }

    /**
     * 对详细搜索后的结果进行分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<User> selDetail(Integer currPage, Search search) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByDetail(search));
        return pageInfo;
    }
}
