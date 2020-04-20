package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.Comment;
import com.wedding.weddingconsulthappiness.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService cs;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.GET)
    public int add(){
        Comment h=new Comment();
        h.setSenderId(2);
        h.setContent("lll");
        h.setHappinessId(0);
        h.setState(0);
        return cs.insert(h);
    }
    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Comment get(){
        return cs.selectByPrimaryKey(0);
    }
    @ResponseBody
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Comment> getAll(){
        return cs.selectAll();
    }

}
