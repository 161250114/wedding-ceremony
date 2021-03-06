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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println("successful");
        return userService.selById(id);
    }

    @RequestMapping("list")
    public List<User> list(){
        return userService.selAll();
    }

    @RequestMapping("memberList")
    public List<User> memberList(){
        List<User> allUsers= userService.selAll();
        for(int i=0;i<allUsers.size();i++){
            if(allUsers.get(i).getUsertype()==0){
                allUsers.remove(i);
                i--;
            }
        }
        List<User> listSort = allUsers.stream().sorted(Comparator.comparing(User::getTrueness).reversed()).collect(Collectors.toList());
        if(listSort.size()>8){
            listSort=listSort.subList(0,8);
        }
        return listSort;
    }

    @RequestMapping("preferList/{user_id}")
    public List<User> preferList(@PathVariable Integer user_id){
        User currentUser = userService.selById(user_id);
        //获取当前年份
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);

        //根据用户的择偶要求来推荐
        Date_standard date_standard = dateStandardService.selByUserId(user_id);

        if(date_standard.getSalary().equals("不限")){
            date_standard.setSalary("");
        }
        if(date_standard.getEducation().equals("不限")){
            date_standard.setEducation("");
        }
        if (date_standard.getAddress().substring(date_standard.getAddress().length()-2).equals("不限")){
            date_standard.setAddress(date_standard.getAddress().substring(0,date_standard.getAddress().length()-3));
        }

        List<User> selectedListByStandard = new ArrayList<>();
        if(date_standard.getMarrige()==3){//婚姻状况不限
            date_standard.setMarrige((byte) 0);
            List<User> selectedListByStandard_1 = userService.selByStandard(date_standard);
            if(selectedListByStandard_1!= null) {
                selectedListByStandard.addAll(selectedListByStandard_1);
            }
            date_standard.setMarrige((byte) 1);
            List<User> selectedListByStandard_2 = userService.selByStandard(date_standard);
            if(selectedListByStandard_2!= null) {
                selectedListByStandard.addAll(selectedListByStandard_2);
            }
            date_standard.setMarrige((byte) 2);
            List<User> selectedListByStandard_3 = userService.selByStandard(date_standard);
            if(selectedListByStandard_3!= null) {
                selectedListByStandard.addAll(selectedListByStandard_3);
            }
        }
        else {
            selectedListByStandard = userService.selByStandard(date_standard);
        }

        for(int i=0;i<selectedListByStandard.size();i++){//性别相反
            if(selectedListByStandard.get(i).getSex() == currentUser.getSex()){
                selectedListByStandard.remove(i);
                i--;
            }
        }

        for(int i=0;i<selectedListByStandard.size();i++){//年龄在范围之内
            //获取用户出生年份
            Date birthday= selectedListByStandard.get(i).getBirthday();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
            String birth_year=sdf.format(birthday).substring(0,4);
            //得到年龄
            int age = year-Integer.parseInt(birth_year);
            if(age<date_standard.getAgemin()||age>date_standard.getAgemax()){
                selectedListByStandard.remove(i);
                i--;
            }
        }
//        System.out.println(selectedListByStandard.size());
        if(selectedListByStandard.size()>=8){
            return selectedListByStandard.subList(selectedListByStandard.size()-8,selectedListByStandard.size());
        }
//        else {
//            //扩大范围
//            date_standard.setEducation("");
//            date_standard.setAgemin(20);
//            date_standard.setAgemax(50);
//            date_standard.setHeightmin(0);
//            date_standard.setHeightmax(300);
//
//            if(date_standard.getMarrige()==3){//婚姻状况不限
//                date_standard.setMarrige((byte) 0);
//                List<User> selectedListByStandard_1 = userService.selByStandard(date_standard);
//                if(selectedListByStandard_1!=null) {
//                    selectedListByStandard.addAll(selectedListByStandard_1);
//                }
//                date_standard.setMarrige((byte) 1);
//                List<User> selectedListByStandard_2 = userService.selByStandard(date_standard);
//                if(selectedListByStandard_2!=null) {
//                    selectedListByStandard.addAll(selectedListByStandard_2);
//                }
//                date_standard.setMarrige((byte) 2);
//                List<User> selectedListByStandard_3 = userService.selByStandard(date_standard);
//                if(selectedListByStandard_3!=null) {
//                    selectedListByStandard.addAll(selectedListByStandard_3);
//                }
//            }
//            else {
//                selectedListByStandard = userService.selByStandard(date_standard);
//            }
//
//            for(int i=0;i<selectedListByStandard.size();i++){
//                if(selectedListByStandard.get(i).getSex()== currentUser.getSex()){
//                    selectedListByStandard.remove(i);
//                    i--;
//                }
//            }
//
//            for(int i=0;i<selectedListByStandard.size();i++){//年龄在范围之内
//                //获取用户出生年份
//                Date birthday= selectedListByStandard.get(i).getBirthday();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
//                String birth_year=sdf.format(birthday).substring(0,4);
//                //得到年龄
//                int age = year-Integer.parseInt(birth_year);
//                if(age<date_standard.getAgemin()||age>date_standard.getAgemax()){
//                    selectedListByStandard.remove(i);
//                    i--;
//                }
//            }
//        }
        return selectedListByStandard;
    }

    @RequestMapping("label_search/{label}&{user_id}")
    public List<User> labelSearch(@PathVariable String label, @PathVariable Integer user_id){
        return userService.selByLabel(label, user_id);
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
