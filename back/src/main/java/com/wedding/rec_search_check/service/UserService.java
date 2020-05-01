package com.nju.wedding.service;

import com.github.pagehelper.PageInfo;
import com.nju.wedding.model.Search;
import com.nju.wedding.model.Standard;
import com.nju.wedding.model.User;

import java.util.List;

public interface UserService {
    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public User selById(Integer id);

    /**
     * 查所有记录
     * @return
     */
    public List<User> selAll();

    /**
     * 按标签查询
     * @return
     */
    public List<User> selByLabel(String label);

    /**
     * 详细查询
     * @return
     */
    public List<User> selByDetail(Search search);

    /**
     * 根据择偶标准查询
     * @return
     */
    public List<User> selByStandard(Standard standard);

    /**
     * 新增
     * @param user
     */
    public void add(User user);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param user
     */
    public void update(User user);

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<User> selAll(Integer currPage);

    /**
     * 对按标签搜索后的结果进行分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<User> selLabel(Integer currPage, String label) ;

    /**
     * 对详细搜索后的结果进行分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<User> selDetail(Integer currPage, Search search) ;

}
