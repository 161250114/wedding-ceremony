package com.wedding.rec_search_check.controller;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.Standard;
import com.wedding.rec_search_check.service.StandardService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/standard")
public class StandardController {

    @Resource
    private StandardService standardService;

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
    public Standard load(@PathVariable Integer id){
        return standardService.selById(id);
    }

    @RequestMapping("select/{user_id}")
    public Standard select(@PathVariable Integer user_id){
        return standardService.selByUserId(user_id);
    }

    @RequestMapping("list")
    public List<Standard> list(){
        return standardService.selAll();
    }

    @RequestMapping("query/{page}")
    public PageInfo<Standard> query(@PathVariable Integer page){
        return standardService.selAll(page);
    }

    /**
     * 新增
     * @param standard
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody Standard standard){
        standardService.add(standard);
        return "succ";
    }
}
