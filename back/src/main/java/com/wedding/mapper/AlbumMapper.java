package com.wedding.mapper;

import com.wedding.model.po.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Album record);

    Album selectByPrimaryKey(Integer id);

    List<Album> selectAll();

    int updateByPrimaryKey(Album record);
}