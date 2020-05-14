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
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Wedding_record>list= (List<Wedding_record>) redisTemplate.opsForValue().get("Wedding_record");
        if(list==null){
            list=wrs.selectAll();
            redisTemplate.opsForValue().set("Wedding_record",list);
        }
        wr.setId(list.size());
        if(wrs.insert(wr)==1){
            list.add(wr);
            redisTemplate.opsForValue().set("Wedding_record",list);
            return 1;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.POST)
    public Wedding_record get(@RequestBody Integer id) {
        return wrs.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<WeddingRecordVO> getAll(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Wedding_record>list= (List<Wedding_record>) redisTemplate.opsForValue().get("Wedding_record");
        if(list==null){
            list=wrs.selectAll();
            redisTemplate.opsForValue().set("Wedding_record",list);
        }
        List<WeddingRecordVO>result=new ArrayList<>();
        for(Wedding_record w:list){
            result.add(new WeddingRecordVO(w));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public int update(@RequestBody WeddingRecordVO vo){
        Wedding_record w=wrs.selectByPrimaryKey(vo.getId());
        w.setResult(vo.getResult());
        if(wrs.updateByPrimaryKey(w)==1){
            redisTemplate.opsForValue().set("Wedding_record",null);
            return 1;
        }
        return 0;
    }
}
