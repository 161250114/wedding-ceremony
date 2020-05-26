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
    HappinessService happinessService;
    @Autowired
    HappinessLikesService happinessLikesService;
    @Autowired
    UserInfoService userInfoService;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int add(@RequestBody Happiness h, HttpServletRequest request){
        return happinessService.add(h);
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.POST)
    public List<Happiness> get(@RequestBody int[] Ids){
        return happinessService.get(Ids);
    }
    @ResponseBody
    @RequestMapping(value="/getLikes",method = RequestMethod.POST)
    public List<Integer> getLikes(@RequestBody int[] Ids){
        return happinessLikesService.getLikes(Ids);
    }

    @ResponseBody
    @RequestMapping(value="/getMyLikes",method = RequestMethod.POST)
    public List<Boolean> getMyLikes(@RequestBody int[] Ids,HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        int userId=-1;
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            userId=loginVO.getUserid();
        }
        return happinessLikesService.getMyLikes(Ids,userId);
    }


    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Happiness> getAll(){
        return happinessService.getAll();
    }

    @ResponseBody
    @RequestMapping(value="/getId",method = RequestMethod.GET)
    public int getId(){
        return happinessService.getId();
    }
    @ResponseBody
    @RequestMapping(value="/del",method = RequestMethod.POST)
    public int del(@RequestBody int id){
        return happinessService.del(id);
    }
    @ResponseBody
    @RequestMapping(value="/updatel",method = RequestMethod.POST)
    public int updatel(@RequestBody int id,HttpServletRequest httpServletRequest){
        int userId=-1;
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            userId=loginVO.getUserid();
        }
        return happinessLikesService.addl(id,userId);
    }
    @ResponseBody
    @RequestMapping(value="/getAlll",method = RequestMethod.GET)
    public List<Happiness_likes> getAlll(){
        return happinessLikesService.getAlll();
    }

    @ResponseBody
    @RequestMapping(value="/getName",method = RequestMethod.POST)
    public String getName(@RequestBody int id){
        return happinessService.getFullName(id);
    }

}
