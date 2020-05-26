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
    HappinessPhotoService happinessPhotoService;

    @ResponseBody
    @RequestMapping(value="/addlist",method = RequestMethod.POST)
    public int add(@RequestBody HappinessPhotoVO[] list, HttpServletRequest request){
        return happinessPhotoService.add(list);
    }
    @ResponseBody
    @RequestMapping(value="/getPhotoList",method = RequestMethod.POST)
    public List<List<String>> getPhotoList(@RequestBody int[] Ids){
        return happinessPhotoService.getPhotoList(Ids);
    }


}
