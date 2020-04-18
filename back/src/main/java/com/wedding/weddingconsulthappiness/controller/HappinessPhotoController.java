package com.wedding.weddingconsulthappiness.controller;


import com.wedding.model.po.Happiness_photo;
import com.wedding.usermanage.service.HappinessPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/happinessphoto")
public class HappinessPhotoController {
    @Autowired
    HappinessPhotoService hps;
    @ResponseBody
    @RequestMapping(value="/add",method= RequestMethod.GET)
    public int add(){
        Happiness_photo hp=new Happiness_photo();
        hp.setHappinessId(0);
        return hps.insert(hp);
    }

    @ResponseBody
    @RequestMapping(value="/get",method= RequestMethod.GET)
    public Happiness_photo get(){
        return hps.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method= RequestMethod.GET)
    public List<Happiness_photo> getAll(){
        return hps.selectAll();
    }



    @ResponseBody
    @RequestMapping(value="/update",method= RequestMethod.GET)
    public int update(){
        Happiness_photo hp=new Happiness_photo();
        hp.setHappinessId(4);
        return hps.updateByPrimaryKey(hp);
    }


}
