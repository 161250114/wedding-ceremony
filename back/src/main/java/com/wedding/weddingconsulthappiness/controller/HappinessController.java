package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.Happiness;
import com.wedding.model.po.Happiness_photo;
import com.wedding.weddingconsulthappiness.service.HappinessPhotoService;
import com.wedding.weddingconsulthappiness.service.HappinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/happiness")
public class HappinessController {
    @Autowired
    HappinessService hs;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int add(@RequestBody Happiness h, HttpServletRequest request){
        h.setId(hs.selectAll().size());
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

    @ResponseBody
    @RequestMapping(value="/getId",method = RequestMethod.GET)
    public int getId(){
        return hs.selectAll().size();
    }

}
