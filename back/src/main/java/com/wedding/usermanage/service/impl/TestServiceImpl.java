package com.wedding.usermanage.service.impl;

import com.wedding.usermanage.service.TestService;
import com.wedding.mapper.AlbumMapper;
import com.wedding.model.po.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public void test() {
        Album album=new Album();
        album.setCurrentNumber(2);
        album.setMaxNumber(5);
        albumMapper.insert(album);
    }
}