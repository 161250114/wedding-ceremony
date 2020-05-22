package com.wedding.weddingconsulthappiness.service.impl;

import com.wedding.mapper.CommentMapper;
import com.wedding.model.po.Comment;
import com.wedding.weddingconsulthappiness.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value="CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper cm;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public int add(Comment c) {
        List<Comment>list=getCFROMRedis();
        c.setId(list.size());
        if(cm.insert(c)==1){
            list.add(c);
            redisTemplate.opsForValue().set("Comment",list);
            return 1;
        }
        return 0;
    }

    @Override
    public List<List<Comment>> getCommentList(int[] ids) {
        List<Comment>list=getCFROMRedis();
        List<List<Comment>>result=new ArrayList<>();
        for(int id:ids){
            List<Comment>r=new ArrayList<>();
            for(Comment c:list){
                if(c.getHappinessId()==id){
                    r.add(c);
                }
            }
            result.add(r);
        }
        return result;
    }

    public List<Comment> getCFROMRedis(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Comment>list= (List<Comment>) redisTemplate.opsForValue().get("Comment");
        if(list==null){
            list=cm.selectAll();
            redisTemplate.opsForValue().set("Comment",list);
        }
        return list;
    }
}
