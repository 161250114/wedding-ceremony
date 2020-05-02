package com.wedding.rec_search_check.controller;

import com.github.pagehelper.PageInfo;
import com.wedding.model.po.LabelHeat;
import com.wedding.model.po.UserLabel;
import com.wedding.rec_search_check.service.LabelHeatService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        System.out.println(userLabel.toString());
        labelHeatService.addHeat(userLabel.getLabel());
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
