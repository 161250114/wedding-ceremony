package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.Comment;
import com.wedding.model.po.Happiness;
import com.wedding.model.po.Happiness_likes;
import com.wedding.model.po.Happiness_photo;
import com.wedding.usermanage.service.UserInfoService;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.weddingconsulthappiness.service.CommentService;
import com.wedding.weddingconsulthappiness.service.HappinessLikesService;
import com.wedding.weddingconsulthappiness.service.HappinessPhotoService;
import com.wedding.weddingconsulthappiness.service.HappinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/happiness")
public class HappinessController {
    @Autowired
    HappinessService hs;
    @Autowired
    HappinessLikesService hls;
    @Autowired
    HappinessPhotoService hps;
    @Autowired
    CommentService cs;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int add(@RequestBody Happiness h, HttpServletRequest request){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness>list= (List<Happiness>) redisTemplate.opsForValue().get("Happiness");
        if(list==null){
            list=hs.selectAll();
            redisTemplate.opsForValue().set("Happiness",list);
        }
        h.setId(list.size());
        if(hs.insert(h)==1){
            list.add(h);
            redisTemplate.opsForValue().set("Happiness",h);
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.POST)
    public List<Happiness> get(@RequestBody int[] Ids){
        List<Integer>ids=new ArrayList<>();
        for(int i:Ids){
            ids.add(i);
        }
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness>list= (List<Happiness>) redisTemplate.opsForValue().get("Happiness");
        if(list==null){
            list=hs.selectAll();
            redisTemplate.opsForValue().set("Happiness",list);
        }
        List<Happiness>result=new ArrayList<Happiness>();
        for(Happiness h:list){
            if(ids.contains(h.getSenderId())&&h.getState()==0){
                result.add(h);
            }
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value="/getLikes",method = RequestMethod.POST)
    public List<Integer> getLikes(@RequestBody int[] Ids){
        List<Integer>ids=new ArrayList<>();
        for(int i:Ids){
            ids.add(i);
        }
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness_likes>list= (List<Happiness_likes>) redisTemplate.opsForValue().get("Happiness_likes");
        if(list==null){
            list=hls.selectAll();
            redisTemplate.opsForValue().set("Happiness_likes",list);
        }
        List<Integer>result=new ArrayList<>();
        for(Integer id:ids){
            Integer num=0;
            for(Happiness_likes hl:list){
                if(hl.getHappinessId()==id&&hl.getState()==0){
                    num++;
                }
            }
            result.add(num);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/getMyLikes",method = RequestMethod.POST)
    public List<Boolean> getMyLikes(@RequestBody int[] Ids,HttpServletRequest httpServletRequest){
        List<Integer>ids=new ArrayList<>();
        for(int i:Ids){
            ids.add(i);
        }
        HttpSession session=httpServletRequest.getSession(false);
        int userId=-1;
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            userId=loginVO.getUserid();
        }
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness_likes>list= (List<Happiness_likes>) redisTemplate.opsForValue().get("Happiness_likes");
        if(list==null){
            list=hls.selectAll();
            redisTemplate.opsForValue().set("Happiness_likes",list);
        }
        List<Boolean>result=new ArrayList<>();
        for(Integer id:ids){
            boolean r=false;
            for(Happiness_likes hl:list){
                if(hl.getHappinessId()==id&&hl.getLikeId()==userId&&hl.getState()==0){
                    r=true;
                }
            }
            result.add(r);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/getPhotoList",method = RequestMethod.POST)
    public List<List<String>> getPhotoList(@RequestBody int[] Ids){
        List<Integer>ids=new ArrayList<>();
        for(int i:Ids){
            ids.add(i);
        }
        List<Happiness_photo>list=hps.selectAll();

        List<List<String>>result=new ArrayList<>();
        for(Integer id:ids){
            List<String>r=new ArrayList<>();
            for(Happiness_photo hp:list){
                if(hp.getHappinessId()==id){
                    r.add(new String(hp.getPhoto()));
                }
            }
            result.add(r);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/getCommentList",method = RequestMethod.POST)
    public List<List<Comment>> getCommentList(@RequestBody int[] ids){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Comment>list= (List<Comment>) redisTemplate.opsForValue().get("Comment");
        if(list==null){
            list=cs.selectAll();
            redisTemplate.opsForValue().set("Comment",list);
        }
        List<List<Comment>>result=new ArrayList<>();
        for(Integer id:ids){
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
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Happiness> getAll(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness>list= (List<Happiness>) redisTemplate.opsForValue().get("Happiness");
        if(list==null){
            list=hs.selectAll();
            redisTemplate.opsForValue().set("Happiness",list);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value="/getId",method = RequestMethod.GET)
    public int getId(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness>list= (List<Happiness>) redisTemplate.opsForValue().get("Happiness");
        if(list==null){
            list=hs.selectAll();
            redisTemplate.opsForValue().set("Happiness",list);
        }
        return list.size();
    }
    @ResponseBody
    @RequestMapping(value="/del",method = RequestMethod.POST)
    public int del(@RequestBody int id){
        Happiness h=hs.selectByPrimaryKey(id);
        h.setState(1);
        redisTemplate.opsForValue().set("Happiness",null);
        return hs.updateByPrimaryKey(h);
    }
    @ResponseBody
    @RequestMapping(value="/updatel",method = RequestMethod.POST)
    public int addl(@RequestBody int id,HttpServletRequest httpServletRequest){
        int userId=-1;
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            userId=loginVO.getUserid();
        }
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness_likes>list= (List<Happiness_likes>) redisTemplate.opsForValue().get("Happiness_likes");
        if(list==null){
            list=hls.selectAll();
            redisTemplate.opsForValue().set("Happiness_likes",list);
        }
        for(Happiness_likes h:list){
            if(h.getLikeId()==userId&&h.getHappinessId()==id){
                h.setState(1-h.getState());
                if(hls.updateByPrimaryKey(h)==1){
                    redisTemplate.opsForValue().set("Happiness_likes",null);
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
        Happiness_likes h=new Happiness_likes(hls.selectAll().size(),id,userId,0);
        if(hls.insert(h)==1){
            list.add(h);
            redisTemplate.opsForValue().set("Happiness_likes",list);
            return 1;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping(value="/getAlll",method = RequestMethod.GET)
    public List<Happiness_likes> getAlll(){
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Happiness_likes>list= (List<Happiness_likes>) redisTemplate.opsForValue().get("Happiness_likes");
        if(list==null){
            list=hls.selectAll();
            redisTemplate.opsForValue().set("Happiness_likes",list);
        }
        return list;
    }
}
