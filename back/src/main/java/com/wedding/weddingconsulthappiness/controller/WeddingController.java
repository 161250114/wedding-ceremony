package com.wedding.weddingconsulthappiness.controller;


import com.wedding.model.po.Wedding;
import com.wedding.weddingconsulthappiness.service.WeddingService;
import com.wedding.weddingconsulthappiness.vo.WeddingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wedding")
public class WeddingController {
    @Autowired
    WeddingService ws;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int addsm(@RequestBody Wedding w, HttpServletRequest request){
        w.setId(ws.selectAll().size());
        return ws.insert(w);
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Wedding get(){
        return ws.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Wedding> getAll(){
        List list=ws.selectAll();
        List result=new ArrayList();
        for(int i=0;i<list.size();i++){
            result.add(new WeddingVO((Wedding)list.get(i)));
            System.out.println(((WeddingVO)result.get(i)).getEnd());
        }
        return result;
    }

}
