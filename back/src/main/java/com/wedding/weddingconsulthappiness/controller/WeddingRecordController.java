package com.wedding.weddingconsulthappiness.controller;


import com.wedding.model.po.Wedding_record;
import com.wedding.weddingconsulthappiness.service.WeddingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/weddingrecord")
public class WeddingRecordController {
    @Autowired
    WeddingRecordService wrs;
    @ResponseBody
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public int add(@RequestBody Wedding_record wr){
        return wrs.insert(wr);
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.POST)
    public Wedding_record get(@RequestBody Integer id) {
        return wrs.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Wedding_record> getAll(){
        return wrs.selectAll();
    }
}
