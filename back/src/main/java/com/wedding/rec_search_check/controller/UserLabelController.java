package com.nju.wedding.controller;

import com.github.pagehelper.PageInfo;
import com.nju.wedding.model.UserLabel;
import com.nju.wedding.service.UserLabelService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/userLabel")
public class UserLabelController {

    @Resource
    private UserLabelService userLabelService;


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
    public UserLabel load(@PathVariable Integer id){
        return userLabelService.selById(id);
    }

    @RequestMapping("select/{user_id}")
    public UserLabel select(@PathVariable Integer user_id){
        return userLabelService.selByUserId(user_id);
    }

    @RequestMapping("screen/{label}")
    public List<UserLabel> screen(@PathVariable String label){
        return userLabelService.selByLabel(label);
    }

    @RequestMapping("list")
    public List<UserLabel> list(){
        return userLabelService.selAll();
    }

    @RequestMapping("query/{page}")
    public PageInfo<UserLabel> query(@PathVariable Integer page){
        return userLabelService.selAll(page);
    }

    /**
     * 新增
     * @param userLabel
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody UserLabel userLabel){
        userLabelService.add(userLabel);
        return "succ";
    }
}
