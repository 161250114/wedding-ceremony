package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.System_message;
import com.wedding.weddingconsulthappiness.service.SystemMessageService;
import com.wedding.weddingconsulthappiness.vo.MessageState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/systemmessage")
public class SystemMessageController {
    @Autowired
    SystemMessageService systemMessageService;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int addsm(@RequestBody System_message sm){
        return systemMessageService.addSystemMessage(sm);
    }
    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public List<System_message> getsm(@RequestBody Integer number){
        return systemMessageService.getsm(number);
    }

    @ResponseBody
    @RequestMapping(value = "/read",method = RequestMethod.POST)
    public int read(@RequestBody String str){
        return systemMessageService.read(str);
    }

    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<System_message> getAll(){
        return systemMessageService.getAll();
    }
    @ResponseBody
    @RequestMapping(value = "/getState",method = RequestMethod.GET)
    public List<MessageState> getState(){
        return systemMessageService.getState();
    }


    @ResponseBody
    @RequestMapping(value = "/getMyState",method = RequestMethod.POST)
    public int getMyState(@RequestBody int id){
        return systemMessageService.getMyState(id);
    }


}
