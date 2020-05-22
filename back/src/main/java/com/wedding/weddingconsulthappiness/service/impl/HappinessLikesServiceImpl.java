package com.wedding.weddingconsulthappiness.service.impl;

import com.wedding.mapper.Happiness_likesMapper;
import com.wedding.model.po.Happiness_likes;
import com.wedding.weddingconsulthappiness.service.HappinessLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value="HappinessLikesService")
public class HappinessLikesServiceImpl implements HappinessLikesService {
    @Autowired
    Happiness_likesMapper hlm;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Override
    public int addl(int id, int userId) {
        List<Happiness_likes>list=getHlFromRedis();
        for(Happiness_likes h:list){
            if(h.getLikeId()==userId&&h.getHappinessId()==id){
                h.setState(1-h.getState());
                if(hlm.updateByPrimaryKey(h)==1){
                    redisTemplate.opsForValue().set("Happiness_likes",null);
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
        Happiness_likes h=new Happiness_likes(list.size(),id,userId,0);
        if(hlm.insert(h)==1){
            list.add(h);
            redisTemplate.opsForValue().set("Happiness_likes",list);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Happiness_likes> getAlll() {
        List<Happiness_likes>list=getHlFromRedis();
        return list;
    }

    @Override
    public List<Boolean> getMyLikes(int[] Ids, int userId) {
        List<Integer>ids=new ArrayList<>();
        for(int i:Ids){
            ids.add(i);
        }
        List<Happiness_likes>list=getHlFromRedis();
        List<Boolean>result=new ArrayList<>();
        for(Integer id:ids){
            boolean r=false;
            for(Happiness_likes hl:list){
                if(hl.getHappinessId()-id==0&&hl.getLikeId()-userId==0&&hl.getState()==0){
                    r=true;
                }
            }
            result.add(r);
        }
        return result;
    }

    @Override
    public List<Integer> getLikes(int[] Ids) {
        List<Integer>ids=new ArrayList<>();
        for(int i:Ids){
            ids.add(i);
        }
        List<Happiness_likes>list=getHlFromRedis();
        List<Integer>result=new ArrayList<>();
        for(Integer id:ids){
            Integer num=0;
            for(Happiness_likes hl:list){
                if(hl.getHappinessId()-id==0&&hl.getState()==0){
                    num++;
                }
            }
            result.add(num);
        }
        return result;
    }

    public List<Happiness_likes> getHlFromRedis(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness_likes>list= (List<Happiness_likes>) redisTemplate.opsForValue().get("Happiness_likes");
        if(list==null){
            list=hlm.selectAll();
            redisTemplate.opsForValue().set("Happiness_likes",list);
        }
        return list;
    }
}
