package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.System_message;
import com.wedding.weddingconsulthappiness.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/systemmessage")
public class SystemMessageController {
    @Autowired
    SystemMessageService ts;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int addsm(@RequestBody System_message sm, HttpServletRequest resquest){
        sm.setId(ts.getId());
        return ts.addSystemMessage(sm);
    }
    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public List<System_message> getsm(@RequestBody Integer number){
        List<System_message>list=ts.selectAll();
        ArrayList<System_message>result=new ArrayList<>();
        for(System_message sm:list){
            if(sm.getSenderId()+sm.getReceiverId()==number){
                result.add(sm);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<System_message> getAll(){
        return ts.selectAll();
    }

    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public int update(){
        System_message sm=new System_message();
        sm.setId(0);
        sm.setSenderId(1);
        sm.setReceiverId(3);
        sm.setContent("hhh");
        sm.setState(1);
        return ts.updateByPrimaryKey(sm);
    }
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public int deletesm(){
        return ts.deleteByPrimaryKey(0);
    }


}
