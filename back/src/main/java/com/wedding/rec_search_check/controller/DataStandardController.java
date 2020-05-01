package com.wedding.rec_search_check.controller;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.Date_standard;
import com.wedding.rec_search_check.service.DateStandardService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/data_standard")
public class DataStandardController {

    @Resource
    private DateStandardService dateStandardService;

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
    public Date_standard load(@PathVariable Integer id){
        return dateStandardService.selById(id);
    }

    @RequestMapping("select/{user_id}")
    public Date_standard select(@PathVariable Integer user_id){
        return dateStandardService.selByUserId(user_id);
    }

    @RequestMapping("list")
    public List<Date_standard> list(){
        return dateStandardService.selAll();
    }

    @RequestMapping("query/{page}")
    public PageInfo<Date_standard> query(@PathVariable Integer page){
        return dateStandardService.selAll(page);
    }

    /**
     * 新增
     * @param date_standard
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody Date_standard date_standard){
        dateStandardService.add(date_standard);
        return "succ";
    }
}
