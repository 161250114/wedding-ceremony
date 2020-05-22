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
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int add(@RequestBody Happiness h, HttpServletRequest request){
        return hs.add(h);
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.POST)
    public List<Happiness> get(@RequestBody int[] Ids){
        return hs.get(Ids);
    }
    @ResponseBody
    @RequestMapping(value="/getLikes",method = RequestMethod.POST)
    public List<Integer> getLikes(@RequestBody int[] Ids){
        return hls.getLikes(Ids);
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
        return hls.getMyLikes(Ids,userId);
    }

    @ResponseBody
    @RequestMapping(value="/getPhotoList",method = RequestMethod.POST)
    public List<List<String>> getPhotoList(@RequestBody int[] Ids){
        return hps.getPhotoList(Ids);
    }

    @ResponseBody
    @RequestMapping(value="/getCommentList",method = RequestMethod.POST)
    public List<List<Comment>> getCommentList(@RequestBody int[] ids){
        return cs.getCommentList(ids);

    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Happiness> getAll(){
        return hs.getAll();
    }

    @ResponseBody
    @RequestMapping(value="/getId",method = RequestMethod.GET)
    public int getId(){
        return hs.getId();
    }
    @ResponseBody
    @RequestMapping(value="/del",method = RequestMethod.POST)
    public int del(@RequestBody int id){
        return hs.del(id);
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
        return hls.addl(id,userId);
    }
    @ResponseBody
    @RequestMapping(value="/getAlll",method = RequestMethod.GET)
    public List<Happiness_likes> getAlll(){
        return hls.getAlll();
    }

}
