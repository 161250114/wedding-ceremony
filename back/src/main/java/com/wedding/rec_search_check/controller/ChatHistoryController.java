package com.nju.wedding.controller;

import com.github.pagehelper.PageInfo;
import com.nju.wedding.model.ChatHistory;
import com.nju.wedding.service.ChatHistoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ChatHistory select(@PathVariable Integer user_id){
        return chatHistoryService.selByUserId(user_id);
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