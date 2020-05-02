package com.wedding.rec_search_check.service;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.Album_photo;

import java.util.List;

public interface AlbumService {
    /**
     * 根据键加载记录
     * @param id
     * @return
     */
    public Album_photo selById(Integer id);

    /**
     * 查所有记录
     * @return
     */
    public List<Album_photo> selAll();

    /**
     * 根据albumid查照片
     * @return
     */
    public List<Album_photo> selByAlbumId(Integer albumid);

    /**
     * 新增
     * @param album_photo
     */
    public void add(Album_photo album_photo);

    /**
     * 删除
     * @param id
     */
    public void del(Integer id);

    /**
     * 修改
     * @param album_photo
     */
    public void update(Album_photo album_photo);

    /**
     * 分页查询
     * @param currPage 第几页
     * @return
     */
    public PageInfo<Album_photo> selAll(Integer currPage);

}
