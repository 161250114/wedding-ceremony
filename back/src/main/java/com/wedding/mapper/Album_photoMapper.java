package com.wedding.mapper;

import com.wedding.model.po.Album_photo;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface Album_photoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Album_photo record);

    Album_photo selectByPrimaryKey(Integer id);

    List<Album_photo> selectAll();

    int updateByPrimaryKey(Album_photo record);

    List<Album_photo> selectByAlbumId(Integer albumid);
}