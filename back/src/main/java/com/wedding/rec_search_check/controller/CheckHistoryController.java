package com.wedding.rec_search_check.controller;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.CheckHistory;
import com.wedding.rec_search_check.service.CheckHistoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/checkHistory")
public class CheckHistoryController {

    @Resource
    private CheckHistoryService checkHistoryService;

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
    public CheckHistory load(@PathVariable Integer id){
        return checkHistoryService.selById(id);
    }

    @RequestMapping("select/{user_id}")
    public List<CheckHistory> select(@PathVariable Integer user_id){
        return checkHistoryService.selByUserId(user_id);
    }

    @RequestMapping("list")
    public List<CheckHistory> list(){
        return checkHistoryService.selAll();
    }

    @RequestMapping("query/{page}")
    public PageInfo<CheckHistory> query(@PathVariable Integer page){
        return checkHistoryService.selAll(page);
    }

    /**
     * 新增
     * @param checkHistory
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody CheckHistory checkHistory){
        checkHistoryService.add(checkHistory);
        return "succ";
    }

}
