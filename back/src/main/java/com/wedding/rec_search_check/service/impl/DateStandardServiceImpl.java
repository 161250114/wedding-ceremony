package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wedding.mapper.StandardMapper;
import com.wedding.model.po.Standard;
import com.wedding.rec_search_check.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardMapper standardMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public Standard selById(Integer id) {
        return standardMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    @Override
    public Standard selByUserId(Integer user_id) {
        return standardMapper.selectByUserId(user_id);
    }

    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<Standard> selAll() {
        return standardMapper.selectAll();
    }

    /**
     * 新增
     * @param standard
     */
    @Override
    public void add(Standard standard) {
        standardMapper.insert(standard);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        standardMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param standard
     */
    @Override
    public void update(Standard standard) {
        standardMapper.updateByPrimaryKey(standard);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<Standard> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<Standard> pageInfo = new PageInfo<>(standardMapper.selectAll());
        return pageInfo;
    }
}
