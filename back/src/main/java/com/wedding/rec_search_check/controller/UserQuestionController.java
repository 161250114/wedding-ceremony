package com.wedding.rec_search_check.controller;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.UserQuestion;
import com.wedding.rec_search_check.service.UserQuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/userQuestion")
public class UserQuestionController {

    @Resource
    private UserQuestionService userQuestionService;

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
    public UserQuestion load(@PathVariable Integer id){
        return userQuestionService.selById(id);
    }

    @RequestMapping("select/{user_id}")
    public List<UserQuestion> select(@PathVariable Integer user_id){
        return userQuestionService.selByUserId(user_id);
    }

    @RequestMapping("list")
    public List<UserQuestion> list(){
        return userQuestionService.selAll();
    }

    @RequestMapping("query/{page}")
    public PageInfo<UserQuestion> query(@PathVariable Integer page){
        return userQuestionService.selAll(page);
    }

    /**
     * 新增
     * @param userQuestion
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody UserQuestion userQuestion){
        userQuestionService.add(userQuestion);
        return "succ";
    }

}
