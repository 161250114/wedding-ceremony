package com.wedding.weddingconsulthappiness.controller;

import com.wedding.model.po.Comment;
import com.wedding.weddingconsulthappiness.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public int add(@RequestBody Comment c){
        return commentService.add(c);
    }
    @ResponseBody
    @RequestMapping(value="/getCommentList",method = RequestMethod.POST)
    public List<List<Comment>> getCommentList(@RequestBody int[] ids){
        return commentService.getCommentList(ids);

    }

}
