package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.Happiness;
import com.wedding.usermanage.service.HappinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/happiness")
public class HappinessController {
    @Autowired
    HappinessService hs;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.GET)
    public int add(){
        Happiness h=new Happiness();
        h.setSenderId(2);
        Date date=new Date();
        h.setTime(date);
        h.setContent("lll");
        h.setLikes(0);
        h.setState(0);
        return hs.insert(h);
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Happiness get(){
        return hs.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Happiness> getAll(){
        return hs.selectAll();
    }

}
