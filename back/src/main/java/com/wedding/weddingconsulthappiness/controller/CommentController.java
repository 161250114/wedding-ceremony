package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.Comment;
import com.wedding.weddingconsulthappiness.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService cs;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int add(@RequestBody Comment c){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Comment>list= (List<Comment>) redisTemplate.opsForValue().get("Comment");
        if(list==null){
            list=cs.selectAll();
            redisTemplate.opsForValue().set("Comment",list);
        }
        c.setId(list.size());
        if(cs.insert(c)==1){
            list.add(c);
            redisTemplate.opsForValue().set("Comment",list);
            return 1;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Comment get(){
        return cs.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Comment> getAll(){
        return cs.selectAll();
    }

}
