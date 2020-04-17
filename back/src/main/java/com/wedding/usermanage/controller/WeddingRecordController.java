package com.wedding.usermanage.controller;;

import com.wedding.model.po.Wedding_record;
import com.wedding.usermanage.service.WeddingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/weddingrecord")
public class WeddingRecordController {
    @Autowired
    WeddingRecordService wrs;
    @ResponseBody
    @RequestMapping(value="/add",method= RequestMethod.GET)
    public int add(){
        Wedding_record wr=new Wedding_record();
        wr.setApproverId(4);
        wr.setTime(new Date());
        wr.setResult(1);
        wr.setWeddingId(0);
        return wrs.insert(wr);
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Wedding_record get(){
        return wrs.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Wedding_record> getAll(){
        return wrs.selectAll();
    }
}
