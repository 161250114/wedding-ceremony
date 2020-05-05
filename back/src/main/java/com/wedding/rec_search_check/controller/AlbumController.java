package com.wedding.rec_search_check.controller;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.Album_photo;
import com.wedding.rec_search_check.service.AlbumService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Resource
    private AlbumService albumService;

//    @RequestMapping("get")
//    public ChatHistory get(Integer id){
//        return chatHistoryService.selById(id);
//    }

    /**
     * resultful风格接收参数
     * @param id
     * @return
     */
    @RequestMapping("get/{id}")
    public Album_photo load(@PathVariable Integer id){
        return albumService.selById(id);
    }

    @RequestMapping("select/{albumid}")
    public List<Album_photo> select(@PathVariable Integer albumid){
        return albumService.selByAlbumId(albumid);
    }

    @RequestMapping("list")
    public List<Album_photo> list(){
        return albumService.selAll();
    }

    @RequestMapping("query/{page}")
    public PageInfo<Album_photo> query(@PathVariable Integer page){
        return albumService.selAll(page);
    }

    /**
     * 新增
     * @param album_photo
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody Album_photo album_photo){
        albumService.add(album_photo);
        return "succ";
    }
}
