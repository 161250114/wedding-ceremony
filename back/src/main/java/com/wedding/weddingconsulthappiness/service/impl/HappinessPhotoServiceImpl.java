package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.Happiness_photoMapper;
import com.wedding.model.po.Happiness_photo;
import com.wedding.weddingconsulthappiness.service.HappinessPhotoService;
import com.wedding.weddingconsulthappiness.vo.HappinessPhotoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value="/HappinessPhotoService")
public class HappinessPhotoServiceImpl implements HappinessPhotoService {
    @Autowired
    Happiness_photoMapper hpm;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Override
    public int add(HappinessPhotoVO[] list) {
        int index=hpm.selectAll().size();
        for(HappinessPhotoVO hp:list){
            Happiness_photo h=new Happiness_photo();
            h.setId(index);
            h.setHappinessId(hp.getHappinessId());
            h.setPhoto(hp.getPhoto().getBytes());
            index++;
            hpm.insert(h);
        }
        return 1;
    }

    @Override
    public List<List<String>> getPhotoList(int[] Ids) {
        List<Integer>ids=new ArrayList<>();
        for(int i:Ids){
            ids.add(i);
        }
        List<Happiness_photo>list=hpm.selectAll();
        List<List<String>>result=new ArrayList<>();
        for(Integer id:ids){
            List<String>r=new ArrayList<>();
            for(Happiness_photo hp:list){
                if(hp.getHappinessId()-id==0){
                    r.add(new String(hp.getPhoto()));
                }
            }
            result.add(r);
        }
        return result;
    }
}
