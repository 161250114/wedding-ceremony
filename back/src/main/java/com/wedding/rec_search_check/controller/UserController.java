package com.wedding.rec_search_check.controller;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.Date_standard;
import com.wedding.model.po.Search;
import com.wedding.model.po.User;
import com.wedding.rec_search_check.service.CheckHistoryService;
import com.wedding.rec_search_check.service.DateStandardService;
import com.wedding.rec_search_check.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CheckHistoryService checkHistoryService;

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
    public User load(@PathVariable Integer id){
        return userService.selById(id);
    }

    @RequestMapping("list")
    public List<User> list(){
        return userService.selAll();
    }

    @RequestMapping("preferList/{user_id}")
    public List<User> preferList(@PathVariable Integer user_id){
        User currentUser = userService.selById(user_id);
        //根据用户的择偶要求来推荐，若不够，则放宽要求，只要address，marriage
        Date_standard date_standard = dateStandardService.selByUserId(user_id);
        List<User> selectedListByStandard = userService.selByStandard(date_standard);
        for(int i=0;i<selectedListByStandard.size();i++){
            if(selectedListByStandard.get(i).getSex()== currentUser.getSex()){
                selectedListByStandard.remove(i);
                i--;
            }
        }
//        System.out.println(selectedListByStandard.size());
        if(selectedListByStandard.size()>=8){
            return selectedListByStandard.subList(selectedListByStandard.size()-8,selectedListByStandard.size());
        }
        else {
            date_standard.setEducation("");
            date_standard.setAgemin(20);
            date_standard.setAgemax(50);
            date_standard.setHeightmin(0);
            date_standard.setHeightmax(300);
            selectedListByStandard = userService.selByStandard(date_standard);

            for(int i=0;i<selectedListByStandard.size();i++){
                if(selectedListByStandard.get(i).getSex()== currentUser.getSex()){
                    selectedListByStandard.remove(i);
                    i--;
                }
            }
        }
        return selectedListByStandard;
    }

    @RequestMapping("label_search/{label}&{user_id}")
    public List<User> labelSearch(@PathVariable String label, @PathVariable Integer user_id){
        User currentUser = userService.selById(user_id);
        List<User> userListByLabel = userService.selByLabel(label);
        for(int i=0;i<userListByLabel.size();i++){
            if(userListByLabel.get(i).getSex()== currentUser.getSex()){
                userListByLabel.remove(i);
                i--;
            }
        }
        return userListByLabel;
    }

    @RequestMapping("detail_search")
    public List<User> detailSearch(@RequestBody Search search){
        return userService.selByDetail(search);
    }

    @RequestMapping("query/{page}")
    public PageInfo<User> query(@PathVariable Integer page){
        return userService.selAll(page);
    }

    @RequestMapping("queryLabelSearch/{page}&{label}&{user_id}")
    public PageInfo<User> queryLabelSearch(@PathVariable Integer page, @PathVariable String label, @PathVariable Integer user_id){
        return userService.selLabel(page,label,user_id);
    }

    @RequestMapping("queryDetailSearch/{page}")
    public PageInfo<User> queryDetailSearch(@PathVariable Integer page, @RequestBody Search search){
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
