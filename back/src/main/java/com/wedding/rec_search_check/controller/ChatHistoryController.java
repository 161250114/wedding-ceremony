package com.wedding.rec_search_check.controller;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.ChatHistory;
import com.wedding.rec_search_check.service.ChatHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chatHistory")
public class ChatHistoryController {

    @Resource
    private ChatHistoryService chatHistoryService;

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
    public ChatHistory load(@PathVariable Integer id){
        return chatHistoryService.selById(id);
    }

    @RequestMapping("select/{user_id}")
    public List<ChatHistory> select(@PathVariable Integer user_id){
        return chatHistoryService.selByUserId(user_id);
    }

    @RequestMapping("request/{chatted_user_id}")
    public List<ChatHistory> request(@PathVariable Integer chatted_user_id){
        return chatHistoryService.selByChattedUserId(chatted_user_id);
    }

    @RequestMapping("update")
    public void update(@RequestBody ChatHistory chatHistory){
        chatHistoryService.update(chatHistory);
    }

    @RequestMapping("list")
    public List<ChatHistory> list(){
        return chatHistoryService.selAll();
    }

    @RequestMapping("query/{page}")
    public PageInfo<ChatHistory> query(@PathVariable Integer page){
        return chatHistoryService.selAll(page);
    }

    /**
     * 新增
     * @param chatHistory
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody ChatHistory chatHistory){
        chatHistoryService.add(chatHistory);
        return "succ";
    }

}
