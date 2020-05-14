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
    public int addsm(@RequestBody Wedding w, HttpServletRequest request){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Wedding>list= (List<Wedding>) redisTemplate.opsForValue().get("Wedding");
        if(list==null){
            list=ws.selectAll();
            redisTemplate.opsForValue().set("Wedding",list);
        }
        w.setId(list.size());
        if(ws.insert(w)==1){
            list.add(w);
            redisTemplate.opsForValue().set("Wedding",list);
            return 1;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.POST)
    public List<WeddingVO> get(@RequestBody Integer id){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Wedding>list= (List<Wedding>) redisTemplate.opsForValue().get("Wedding");
        if(list==null){
            list=ws.selectAll();
            redisTemplate.opsForValue().set("Wedding",list);
        }
        ArrayList<WeddingVO> result=new ArrayList<>();
        for(Wedding w:list){
            System.out.println(w.getApplicantId()+""+id);
            if(w.getApplicantId()-id==0){
                System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
                WeddingVO W=new WeddingVO(w);
                result.add(W);
            }
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Wedding> getAll(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Wedding>list= (List<Wedding>) redisTemplate.opsForValue().get("Wedding");
        if(list==null){
            list=ws.selectAll();
            redisTemplate.opsForValue().set("Wedding",list);
        }
        List result=new ArrayList();
        for(int i=0;i<list.size();i++){
            result.add(new WeddingVO((Wedding)list.get(i)));
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public int update(@RequestBody WeddingVO w, HttpServletRequest request){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Wedding>list= (List<Wedding>) redisTemplate.opsForValue().get("Wedding");
        if(list==null){
            list=ws.selectAll();
            redisTemplate.opsForValue().set("Wedding",list);
        }
        List result=new ArrayList();
        Wedding we=new Wedding();
        for(int i=0;i<list.size();i++){
            Wedding W= (Wedding) list.get(i);
            if(W.getId()==w.getId()){
                we=W;
            }
        }
        if(w.getState().equals("取消")){
            we.setState(2);
        }
        else{
            we.setState(1);
        }

        if(ws.updateByPrimaryKey(we)==1){
            redisTemplate.opsForValue().set("Wedding",null);
            return 1;
        }
        return 0;
    }

}
