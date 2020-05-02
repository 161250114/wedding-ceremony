package com.wedding.rec_search_check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wedding.mapper.Album_photoMapper;
import com.wedding.model.po.Album_photo;
import com.wedding.rec_search_check.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private Album_photoMapper album_photoMapper;

    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    @Override
    public Album_photo selById(Integer id) {
        return album_photoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据albumid加载记录
     * @param albumid
     * @return
     */
    @Override
    public List<Album_photo> selByAlbumId(Integer albumid) {
        return album_photoMapper.selectByAlbumId(albumid);
    }

    /**
     * 查所有记录
     * @return
     */
    @Override
    public List<Album_photo> selAll() {
        return album_photoMapper.selectAll();
    }

    /**
     * 新增
     * @param album_photo
     */
    @Override
    public void add(Album_photo album_photo) {
        album_photoMapper.insert(album_photo);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void del(Integer id) {
        album_photoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param album_photo
     */
    @Override
    public void update(Album_photo album_photo) {
        album_photoMapper.updateByPrimaryKey(album_photo);
    }

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    @Override
    public PageInfo<Album_photo> selAll(Integer currPage) {
        if(currPage == null) currPage = 1;
        //设置从第几页开始查询2条
        PageHelper.startPage(currPage,8);
        //分页查询
        PageInfo<Album_photo> pageInfo = new PageInfo<>(album_photoMapper.selectAll());
        return pageInfo;
    }
}
