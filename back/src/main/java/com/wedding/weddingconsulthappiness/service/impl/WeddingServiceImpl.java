package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.WeddingMapper;
import com.wedding.model.po.Wedding;
import com.wedding.weddingconsulthappiness.service.WeddingService;
import com.wedding.weddingconsulthappiness.vo.WeddingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="WeddingService")
public class WeddingServiceImpl implements WeddingService {
    @Autowired
    WeddingMapper wm;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public int addw(Wedding w) {
        List<Wedding>list=getWFromRedis();
        w.setId(list.size());
        if(wm.insert(w)==1){
            list.add(w);
            redisTemplate.opsForValue().set("Wedding",list);
            return 1;
        }
        return 0;
    }

    @Override
    public List<WeddingVO> getw(Integer id) {
        List<Wedding>list=getWFromRedis();
        ArrayList<WeddingVO> result=new ArrayList<>();
        for(Wedding w:list){
            if(w.getApplicantId()-id==0||id==0){
                WeddingVO W=new WeddingVO(w);
                result.add(W);
            }
        }
        return result;
    }

    @Override
    public List<Wedding> getAll() {
        List<Wedding>list=getWFromRedis();
        List result=new ArrayList();
        for(int i=0;i<list.size();i++){
            result.add(new WeddingVO(list.get(i)));
        }
        return result;
    }

    @Override
    public int update(WeddingVO w) {
        List<Wedding>list=getWFromRedis();
        Wedding we=new Wedding();
        for(int i=0;i<list.size();i++){
            Wedding W= list.get(i);
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
        if(wm.updateByPrimaryKey(we)==1){
            redisTemplate.opsForValue().set("Wedding",null);
            return 1;
        }
        return 0;
    }

    public List<Wedding> getWFromRedis(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Wedding>list= (List<Wedding>) redisTemplate.opsForValue().get("Wedding");
        if(list==null){
            list=wm.selectAll();
            redisTemplate.opsForValue().set("Wedding",list);
        }
        return list;
    }
}
