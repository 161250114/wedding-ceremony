package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.HappinessMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.model.po.Happiness;
import com.wedding.model.po.User;
import com.wedding.weddingconsulthappiness.service.HappinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value="HappinessService")
public class HappinessServiceImpl implements HappinessService {
    @Autowired
    HappinessMapper hm;
    @Autowired
    UserMapper um;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Override
    public int add(Happiness h) {
        List<Happiness>list=getHFromRedis();
        h.setId(list.size());
        if(hm.insert(h)==1){
            list.add(h);
            redisTemplate.opsForValue().set("Happiness",list);
        }
        return 0;
    }

    @Override
    public List<Happiness> get(int[] Ids) {
        List<Integer>ids=new ArrayList<>();
        for(int i:Ids){
            ids.add(i);
        }
        List<Happiness>list=getHFromRedis();
        List<Happiness>result=new ArrayList<Happiness>();
        for(Happiness h:list){
            if(ids.contains(h.getSenderId())&&h.getState()==0){
                result.add(h);
            }
        }
        return result;
    }

    @Override
    public List<Happiness> getAll() {
        List<Happiness>list=getHFromRedis();
        return list;
    }

    @Override
    public int getId() {
        List<Happiness>list=getHFromRedis();
        return list.size();
    }

    @Override
    public int del(int id) {
        List<Happiness>list=getHFromRedis();
        Happiness h=new Happiness();
        for(Happiness ha:list){
            if(ha.getId()==id){
                h=ha;
            }
        }
        h.setState(1);
        if(hm.updateByPrimaryKey(h)==1){
            redisTemplate.opsForValue().set("Happiness",null);
            return 1;
        }
        return 0;
    }

    @Override
    public String getFullName(int id) {
        List<User>list=um.selectAll();
        for(User user:list){
            if(user.getId()==id){
                return user.getFullname();
            }
        }
        return "";
    }

    public List<Happiness> getHFromRedis(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness>list= (List<Happiness>) redisTemplate.opsForValue().get("Happiness");
        if(list==null){
            list=hm.selectAll();
            redisTemplate.opsForValue().set("Happiness",list);
        }
        return list;
    }
}
