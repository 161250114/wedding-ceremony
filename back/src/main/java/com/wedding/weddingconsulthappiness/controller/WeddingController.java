package com.wedding.weddingconsulthappiness.controller;


import com.wedding.model.po.Wedding;
import com.wedding.weddingconsulthappiness.service.WeddingService;
import com.wedding.weddingconsulthappiness.vo.WeddingVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wedding")
public class WeddingController {
    @Autowired
    WeddingService ws;
    @Autowired
    RedisTemplate<Object,Object>redisTemplate;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int addw(@RequestBody Wedding w, HttpServletRequest request){
        return ws.addw(w);
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.POST)
    public List<WeddingVO> getw(@RequestBody Integer id){
        return ws.getw(id);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Wedding> getAll(){
        return ws.getAll();
    }
    @ResponseBody
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public int update(@RequestBody WeddingVO w, HttpServletRequest request){
        return ws.update(w);
    }


}
