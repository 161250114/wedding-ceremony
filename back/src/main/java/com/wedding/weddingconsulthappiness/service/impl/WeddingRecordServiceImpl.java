package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.Wedding_recordMapper;
import com.wedding.model.po.Wedding_record;
import com.wedding.weddingconsulthappiness.service.WeddingRecordService;
import com.wedding.weddingconsulthappiness.vo.WeddingRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="WeddingRecordService")
public class WeddingRecordServiceImpl implements WeddingRecordService {
    @Autowired
    Wedding_recordMapper wrm;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Override
    public int add(Wedding_record wr) {
        List<Wedding_record>list=getWrFromRedis();
        wr.setId(list.size());
        if(wrm.insert(wr)==1){
            list.add(wr);
            redisTemplate.opsForValue().set("Wedding_record",list);
            return 1;
        }
        return 0;
    }

    @Override
    public List<WeddingRecordVO> getAll() {
        List<Wedding_record>list=getWrFromRedis();
        List<WeddingRecordVO>result=new ArrayList<>();
        for(Wedding_record w:list){
            result.add(new WeddingRecordVO(w));
        }
        return result;
    }

    @Override
    public int update(WeddingRecordVO vo) {
        Wedding_record w=wrm.selectByPrimaryKey(vo.getId());
        w.setResult(vo.getResult());
        if(wrm.updateByPrimaryKey(w)==1){
            redisTemplate.opsForValue().set("Wedding_record",null);
            return 1;
        }
        return 0;
    }

    public List<Wedding_record> getWrFromRedis(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Wedding_record>list= (List<Wedding_record>) redisTemplate.opsForValue().get("Wedding_record");
        if(list==null){
            list=wrm.selectAll();
            redisTemplate.opsForValue().set("Wedding_record",list);
        }
        return list;
    }
}
