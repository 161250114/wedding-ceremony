package com.nju.wedding.controller;

import com.github.pagehelper.PageInfo;
import com.nju.wedding.model.LabelHeat;
import com.nju.wedding.model.UserLabel;
import com.nju.wedding.service.LabelHeatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/labelHeat")
public class LabelHeatController {
    @Resource
    private LabelHeatService labelHeatService;

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
    public LabelHeat load(@PathVariable Integer id){
        return labelHeatService.selById(id);
    }


    @RequestMapping("list")
    public List<LabelHeat> list(){
        return labelHeatService.selAll();
    }

    @RequestMapping("query/{page}")
    public PageInfo<LabelHeat> query(@PathVariable Integer page){
        return labelHeatService.selAll(page);
    }

    /**
     * 增加热度
     * @param userLabel
     * @return
     */
    @RequestMapping("addHeat")
    public String addHeat(@RequestBody UserLabel userLabel){
        System.out.println(userLabel.toString());
        labelHeatService.addHeat(userLabel.getLabel1());
        labelHeatService.addHeat(userLabel.getLabel2());
        labelHeatService.addHeat(userLabel.getLabel3());
//        System.out.println("succ");
        return "succ";
    }


    @RequestMapping("heat_list")
    public List<LabelHeat> heatList(){
        return labelHeatService.selHottest();
    }

    /**
     * 新增
     * @param labelHeat
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody LabelHeat labelHeat){
        labelHeatService.add(labelHeat);
        return "succ";
    }

}
