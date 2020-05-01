package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wedding.mapper.CheckHistoryMapper;
import com.wedding.model.po.CheckHistory;
import com.wedding.rec_search_check.service.CheckHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckHistoryServiceImpl implements CheckHistoryService {

    @Autowired
    private CheckHistoryMapper checkHistoryMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public CheckHistory selById(Integer id) {
        return checkHistoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户id加载记录
     * @param user_id
     * @return
     */
    @Override
    public List<CheckHistory> selByUserId(Integer user_id) {
        return checkHistoryMapper.selectByUserId(user_id);
    }

    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<CheckHistory> selAll() {
        return checkHistoryMapper.selectAll();
    }

    /**
     * 新增
     * @param checkHistory
     */
    @Override
    public void add(CheckHistory checkHistory) {
        checkHistoryMapper.insert(checkHistory);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        checkHistoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param checkHistory
     */
    @Override
    public void update(CheckHistory checkHistory) {
        checkHistoryMapper.updateByPrimaryKey(checkHistory);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<CheckHistory> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<CheckHistory> pageInfo = new PageInfo<>(checkHistoryMapper.selectAll());
        return pageInfo;
    }
}
