package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.System_message;
import com.wedding.weddingconsulthappiness.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemmessage")
public class SystemMessageController {
    @Autowired
    SystemMessageService ts;
    @PostMapping("/add")
    public int addsm(String data){
        return 1;
    }
    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public System_message getsm(){
        return ts.selectByPrimaryKey(0);
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
