package com.nju.wedding.controller;

import com.github.pagehelper.PageInfo;
import com.nju.wedding.model.CheckHistory;
import com.nju.wedding.model.Search;
import com.nju.wedding.model.Standard;
import com.nju.wedding.model.User;
import com.nju.wedding.service.CheckHistoryService;
import com.nju.wedding.service.StandardService;
import com.nju.wedding.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CheckHistoryService checkHistoryService;

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
    public User load(@PathVariable Integer id){
        return userService.selById(id);
    }

    @RequestMapping("list")
    public List<User> list(){
        return userService.selAll();
    }

    @RequestMapping("preferList/{user_id}")
    public List<User> preferList(@PathVariable Integer user_id){
        //根据用户的择偶要求来推荐，若不够，则放宽要求，只要address，marriage
        Standard standard = standardService.selByUserId(user_id);
        List<User> selectedListByStandard = userService.selByStandard(standard);
//        System.out.println(selectedListByStandard.size());
        if(selectedListByStandard.size()>=8){
            return selectedListByStandard.subList(selectedListByStandard.size()-8,selectedListByStandard.size());
        }
        else {
            standard.setEducation("");
            standard.setYoungest(20);
            standard.setOldest(50);
            standard.setShortest(0);
            standard.setTallest(300);
            selectedListByStandard = userService.selByStandard(standard);
        }
        return selectedListByStandard;
    }

    @RequestMapping("label_search/{label}")
    public List<User> labelSearch(@PathVariable String label){
        return userService.selByLabel(label);
    }

    @RequestMapping("detail_search")
    public List<User> detailSearch(@RequestBody Search search){
        System.out.println(search);
        return userService.selByDetail(search);
    }

    @RequestMapping("query/{page}")
    public PageInfo<User> query(@PathVariable Integer page){
        return userService.selAll(page);
    }

    @RequestMapping("queryLabelSearch/{page}&{label}")
    public PageInfo<User> queryLabelSearch(@PathVariable Integer page,@PathVariable String label){
        System.out.println(page);
        return userService.selLabel(page,label);
    }

    @RequestMapping("queryDetailSearch/{page}")
    public PageInfo<User> queryDetailSearch(@PathVariable Integer page,@RequestBody Search search){
        return userService.selDetail(page,search);
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody User user){
        userService.add(user);
        return "succ";
    }

}
