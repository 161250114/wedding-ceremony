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
    @RequestMapping(value="/get",method = RequestMethod.POST)
    public List<Wedding> get(@RequestBody Integer id){
        List list=ws.selectAll();
        List result=new ArrayList();
        for(int i=0;i<list.size();i++){
            Wedding w= (Wedding) list.get(i);
            if(w.getApplicantId()==id){
                result.add(new WeddingVO((Wedding)list.get(i)));
            }
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Wedding> getAll(){
        List list=ws.selectAll();
        List result=new ArrayList();
        for(int i=0;i<list.size();i++){
            result.add(new WeddingVO((Wedding)list.get(i)));
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public int update(@RequestBody WeddingVO w, HttpServletRequest request){
        List list=ws.selectAll();
        List result=new ArrayList();
        Wedding we=new Wedding();
        for(int i=0;i<list.size();i++){
            Wedding W= (Wedding) list.get(i);
            if(W.getId()==w.getId()){
                we=W;
            }
        }
        if(w.getState().equals("取消")){
            we.setState(2);
        }
        else{
            we.setState(1);
        }

        return ws.updateByPrimaryKey(we);
    }

}
