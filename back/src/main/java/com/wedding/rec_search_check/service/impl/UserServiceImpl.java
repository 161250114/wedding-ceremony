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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        System.out.println("111");
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
    public PageInfo<User> selLabel(Integer currPage, String label, Integer user_id) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        List<User> userListByLabel = userMapper.selectByLabel(label);
        User currentUser = selById(user_id);
        for(int i=0;i<userListByLabel.size();i++){
            if(userListByLabel.get(i).getSex() == currentUser.getSex()){
                userListByLabel.remove(i);
                i--;
            }
        }
        PageInfo<User> pageInfo = new PageInfo<>(userListByLabel);
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
        //设置从第几页开始查询
        PageHelper.startPage(currPage,8);
        //分页查询

        //获取当前年份
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);

        List<User> selectedListByDetail = new ArrayList<>();
        if(search.getMarrige()==3){//婚姻状况不限
            search.setMarrige(0);
            List<User> selectedListByDetail_1 = userMapper.selectByDetail(search);
            if(selectedListByDetail_1!= null) {
                selectedListByDetail.addAll(selectedListByDetail_1);
            }
            search.setMarrige(1);
            List<User> selectedListByDetail_2 = userMapper.selectByDetail(search);
            if(selectedListByDetail_2!= null) {
                selectedListByDetail.addAll(selectedListByDetail_2);
            }
            search.setMarrige(2);
            List<User> selectedListByDetail_3 = userMapper.selectByDetail(search);
            if(selectedListByDetail_3!= null) {
                selectedListByDetail.addAll(selectedListByDetail_3);
            }
            System.out.println(selectedListByDetail.size());
        }
        else {
            selectedListByDetail = userMapper.selectByDetail(search);
        }

        for(int i=0;i<selectedListByDetail.size();i++){//年龄在范围之内
            //获取用户出生年份
            Date birthday= selectedListByDetail.get(i).getBirthday();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
            String birth_year=sdf.format(birthday).substring(0,4);
            //得到年龄
            int age = year-Integer.parseInt(birth_year);

            if(age<search.getYoungest()||age>search.getOldest()){
                selectedListByDetail.remove(i);
                i--;
            }
        }
        PageInfo<User> pageInfo = new PageInfo<>(selectedListByDetail);
        return pageInfo;
    }
}
