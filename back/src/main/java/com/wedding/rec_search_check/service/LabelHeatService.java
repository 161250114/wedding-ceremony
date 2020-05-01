package com.nju.wedding.service;

import com.github.pagehelper.PageInfo;
import com.nju.wedding.model.LabelHeat;
import com.nju.wedding.model.UserLabel;

import java.util.List;

public interface LabelHeatService {
    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public LabelHeat selById(Integer id);

    /**
     * 查所有记录
     * @return
     */
    public List<LabelHeat> selAll();

    /**
     * 新增
     * @param labelHeat
     */
    public void add(LabelHeat labelHeat);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param labelHeat
     */
    public void update(LabelHeat labelHeat);

    /**
     * 增加热度
     * @param label
     */
    public void addHeat(String label);

    /**
     * 根据热度排序
     * @return
     */
    public List<LabelHeat> selHottest();

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<LabelHeat> selAll(Integer currPage);

}
