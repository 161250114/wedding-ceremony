package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.Happiness;
import com.wedding.model.po.Happiness_likes;
import com.wedding.model.po.Happiness_photo;
import com.wedding.weddingconsulthappiness.service.HappinessLikesService;
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
    @Autowired
    HappinessLikesService hls;
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

    @ResponseBody
    @RequestMapping(value="/addlikes",method = RequestMethod.GET)
    public int addl(){
        Happiness_likes hl=new Happiness_likes(0,1,2,0);
        hls.insert(hl);
        hl.setLikeId(5);
        return hls.updateByPrimaryKey(hl);
    }
    @ResponseBody
    @RequestMapping(value="/getAlll",method = RequestMethod.GET)
    public List<Happiness_likes> getAlll(){
        return hls.selectAll();
    }
}
