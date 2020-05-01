package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wedding.mapper.LabelHeatMapper;
import com.wedding.model.po.LabelHeat;
import com.wedding.rec_search_check.service.LabelHeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelHeatServiceImpl implements LabelHeatService {

    @Autowired
    private LabelHeatMapper labelHeatMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public LabelHeat selById(Integer id) {
        return labelHeatMapper.selectByPrimaryKey(id);
    }

    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<LabelHeat> selAll() {
        return labelHeatMapper.selectAll();
    }

    /**
     * 新增
     * @param labelHeat
     */
    @Override
    public void add(LabelHeat labelHeat) {
        labelHeatMapper.insert(labelHeat);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        labelHeatMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param labelHeat
     */
    @Override
    public void update(LabelHeat labelHeat) {
        labelHeatMapper.updateByPrimaryKey(labelHeat);
    }

    /**
     * 增加热度
     * @param label
     */
    @Override
    public void addHeat(String label) {
        labelHeatMapper.updateByLabel(label);
    }

    /**
     * 根据热度排序
     * @return
     */
    @Override
    public List<LabelHeat> selHottest() {
        return labelHeatMapper.selectHottest().subList(0,3);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<LabelHeat> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<LabelHeat> pageInfo = new PageInfo<>(labelHeatMapper.selectAll());
        return pageInfo;
    }
}
