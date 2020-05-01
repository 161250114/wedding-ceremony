package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wedding.mapper.Date_standardMapper;
import com.wedding.model.po.Date_standard;
import com.wedding.rec_search_check.service.DateStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateStandardServiceImpl implements DateStandardService {

    @Autowired
    private Date_standardMapper date_standardMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public Date_standard selById(Integer id) {
        return date_standardMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    @Override
    public Date_standard selByUserId(Integer user_id) {
        return date_standardMapper.selectByUserId(user_id);
    }

    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<Date_standard> selAll() {
        return date_standardMapper.selectAll();
    }

    /**
     * 新增
     * @param date_standard
     */
    @Override
    public void add(Date_standard date_standard) {
        date_standardMapper.insert(date_standard);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        date_standardMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param date_standard
     */
    @Override
    public void update(Date_standard date_standard) {
        date_standardMapper.updateByPrimaryKey(date_standard);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<Date_standard> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<Date_standard> pageInfo = new PageInfo<>(date_standardMapper.selectAll());
        return pageInfo;
    }
}
