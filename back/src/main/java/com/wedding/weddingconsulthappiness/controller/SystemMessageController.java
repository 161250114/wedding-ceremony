package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.System_message;
import com.wedding.weddingconsulthappiness.service.SystemMessageService;
import com.wedding.weddingconsulthappiness.vo.MessageState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/systemmessage")
public class SystemMessageController {
    @Autowired
    SystemMessageService ts;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int addsm(@RequestBody System_message sm, HttpServletRequest resquest){
        sm.setId(ts.getId());
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        if(ts.addSystemMessage(sm)==1){
            redisTemplate.opsForValue().set("System_message",null);
        }
        return 1;
    }
    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public List<System_message> getsm(@RequestBody Integer number){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<System_message>list= (List<System_message>) redisTemplate.opsForValue().get("System_message");
        if(list==null){
            list=ts.selectAll();
            redisTemplate.opsForValue().set("System_message",list);
        }
        ArrayList<System_message>result=new ArrayList<>();
        for(System_message sm:list){
            if(sm.getSenderId()+sm.getReceiverId()==number){
                result.add(sm);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/read",method = RequestMethod.POST)
    public int read(@RequestBody String str){
        int from=Integer.parseInt(str.split("_")[0]);
        int to=Integer.parseInt(str.split("_")[1]);
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<System_message>list= (List<System_message>) redisTemplate.opsForValue().get("System_message");
        if(list==null){
            list=ts.selectAll();
            redisTemplate.opsForValue().set("System_message",list);
        }
        for(System_message s:list){
            if(s.getSenderId()==from&&s.getReceiverId()==to){
                s.setState(1);
                ts.updateByPrimaryKey(s);
                redisTemplate.opsForValue().set("System_message",null);
            }
        }
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<System_message> getAll(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<System_message>list= (List<System_message>) redisTemplate.opsForValue().get("System_message");
        if(list==null){
            list=ts.selectAll();
            redisTemplate.opsForValue().set("System_message",list);
        }
        return list;
    }
    @ResponseBody
    @RequestMapping(value = "/getState",method = RequestMethod.GET)
    public List<MessageState> getState(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<System_message>list= (List<System_message>) redisTemplate.opsForValue().get("System_message");
        if(list==null){
            list=ts.selectAll();
            redisTemplate.opsForValue().set("System_message",list);
        }
        HashMap<Integer,MessageState>map=new HashMap<>();
        for(System_message s:list){
            int id=s.getSenderId();
            if(!map.containsKey(id)){
                MessageState m=new MessageState(id,"","无新消息");
                map.put(id,m);
            }
            if(s.getState()==0){
                MessageState m=new MessageState(id,"","有新消息");
                map.put(id,m);
            }
        }
        List<MessageState>result=new ArrayList<MessageState>();
        for(Integer key:map.keySet()){
            result.add(map.get(key));
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public int update(){
        System_message sm=new System_message();
        sm.setId(0);
        sm.setSenderId(1);
        sm.setReceiverId(3);
        sm.setContent("hhh");
        sm.setState(1);
        return ts.updateByPrimaryKey(sm);
    }

    @ResponseBody
    @RequestMapping(value = "/getMyState",method = RequestMethod.POST)
    public int getMyState(@RequestBody int id){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<System_message>list= (List<System_message>) redisTemplate.opsForValue().get("System_message");
        if(list==null){
            list=ts.selectAll();
            redisTemplate.opsForValue().set("System_message",list);
        }
        int result=0;
        for(System_message s:list){
            if(s.getReceiverId()==id&&s.getState()==0){
                result++;
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public int deletesm(){
        return ts.deleteByPrimaryKey(0);
    }


}
