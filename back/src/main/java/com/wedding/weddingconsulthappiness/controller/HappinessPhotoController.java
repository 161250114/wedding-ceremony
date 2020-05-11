package com.wedding.weddingconsulthappiness.controller;


import com.wedding.model.po.Happiness_photo;
import com.wedding.weddingconsulthappiness.service.HappinessPhotoService;
import com.wedding.weddingconsulthappiness.vo.HappinessPhotoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/happinessphoto")
public class HappinessPhotoController {
    @Autowired
    HappinessPhotoService hps;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
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
    @RequestMapping(value="/addlist",method = RequestMethod.POST)
    public int add(@RequestBody HappinessPhotoVO[] list, HttpServletRequest request){
        int index=hps.selectAll().size();
        for(HappinessPhotoVO hp:list){
            Happiness_photo h=new Happiness_photo();
            h.setId(index);
            h.setHappinessId(hp.getHappinessId());
            h.setPhoto(hp.getPhoto().getBytes());
            index++;
            hps.insert(h);
        }
        return 1;
    }


    @ResponseBody
    @RequestMapping(value="/update",method= RequestMethod.GET)
    public int update(){
        Happiness_photo hp=new Happiness_photo();
        hp.setHappinessId(4);
        return hps.updateByPrimaryKey(hp);
    }


}
