package com.wedding.weddingconsulthappiness.controller;


import com.wedding.model.po.Wedding_record;
import com.wedding.weddingconsulthappiness.service.WeddingRecordService;
import com.wedding.weddingconsulthappiness.vo.WeddingRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/weddingrecord")
public class WeddingRecordController {
    @Autowired
    WeddingRecordService wrs;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @ResponseBody
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public int add(@RequestBody Wedding_record wr){
        return wrs.add(wr);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<WeddingRecordVO> getAll(){
        return wrs.getAll();
    }

    @ResponseBody
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public int update(@RequestBody WeddingRecordVO vo){
        return wrs.update(vo);
    }

}
