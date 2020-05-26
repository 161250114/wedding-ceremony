package com.wedding.weddingconsulthappiness.service.impl;


import com.wedding.mapper.System_messageMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.model.po.System_message;
import com.wedding.model.po.User;
import com.wedding.weddingconsulthappiness.service.SystemMessageService;
import com.wedding.weddingconsulthappiness.vo.MessageState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value="SystemMessageService")
public class SystemMessageServiceImpl implements SystemMessageService {
    @Autowired
    private System_messageMapper smm;

    @Autowired

    private UserMapper um;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Override
    public int addSystemMessage(System_message sm) {
        List<System_message>list=getSmFromRedis();
        sm.setId(list.size());
        if(smm.insert(sm)==1){
            list.add(sm);
            redisTemplate.opsForValue().set("System_message",list);
            return 1;
        }
        return 0;
    }

    @Override
    public List<System_message> getsm(Integer number) {
        List<System_message>list=getSmFromRedis();
        ArrayList<System_message> result=new ArrayList<>();
        for(System_message sm:list){
            if(sm.getSenderId()+sm.getReceiverId()==number){
                result.add(sm);
            }
        }

        return result;
    }

    @Override
    public int read(String str) {
        int from=Integer.parseInt(str.split("_")[0]);
        int to=Integer.parseInt(str.split("_")[1]);
        List<System_message>list=getSmFromRedis();
        for(System_message s:list){
            if(s.getSenderId()==from&&s.getReceiverId()==to){
                s.setState(1);
                if(smm.updateByPrimaryKey(s)==0){
                    return 0;
                }
                redisTemplate.opsForValue().set("System_message",null);
            }
        }
        return 1;
    }

    @Override
    public List<System_message> getAll() {
        List<System_message>list=getSmFromRedis();
        return list;
    }

    @Override
    public List<MessageState> getState() {
        List<User>users=um.selectAll();
        List<System_message>list=getSmFromRedis();
        HashMap<Integer,Integer> map=new HashMap<>();
        for(User user:users){
            if(!map.containsKey(user.getId())){
                map.put(user.getId(),0);
            }
        }
        for(System_message s:list){
            int id=s.getSenderId();
            if(id==0){
                continue;
            }
            else if(s.getState()==0){
                map.put(id,map.get(id)+1);
            }
        }

        List<MessageState>result=new ArrayList<MessageState>();
        for(Integer key:map.keySet()){
            int num=map.get(key);
            for(User user:users){
                if(user.getId()==key){
                    MessageState m=new MessageState(key,user.getUsername(),"有"+map.get(key)+"条新消息");
                    result.add(m);
                }
            }
        }
        Collections.sort(result, new Comparator<MessageState>() {
            @Override
            public int compare(MessageState o1, MessageState o2) {
                String str1=o1.getState();
                String str2=o2.getState();
                int num1=Integer.parseInt(str1.substring(1,str1.length()-4));
                int num2=Integer.parseInt(str2.substring(1,str2.length()-4));
                return num2-num1;
            }
        });
        return result;
    }

    @Override
    public int getMyState(int id) {
        List<System_message>list=getSmFromRedis();
        int result=0;
        for(System_message s:list){
            if(s.getReceiverId()==id&&s.getState()==0){
                result++;
            }
        }
        return result;
    }

    public List<System_message> getSmFromRedis(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<System_message>list= (List<System_message>) redisTemplate.opsForValue().get("System_message");
        if(list==null){
            list=smm.selectAll();
            redisTemplate.opsForValue().set("System_message",list);
        }
        return list;
    }

}
