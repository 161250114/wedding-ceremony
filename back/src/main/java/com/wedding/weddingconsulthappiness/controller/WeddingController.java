package com.wedding.weddingconsulthappiness.controller;


import com.wedding.model.po.Wedding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wedding")
public class WeddingController {
    @Autowired
    WeddingService ws;
    @ResponseBody
    @RequestMapping(value="/add",method= RequestMethod.GET)
    public int add(){
        Wedding w=new Wedding();
        w.setId(1);
        w.setApplicantId(1);
        w.setName("卢本伟");
        w.setDetail("123");
        w.setEmail("123@qq.com");
        w.setLocation("111 111");
        w.setPhone("1332453323");
        w.setTotal(100);
        w.setStart(new Date());
        w.setEnd(new Date());
        return ws.insert(w) ;
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Wedding get(){
        return ws.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Wedding> getAll(){
        return ws.selectAll();
    }

}
