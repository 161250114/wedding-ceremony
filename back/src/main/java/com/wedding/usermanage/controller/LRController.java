package com.wedding.usermanage.controller;


import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.LRService;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class LRController {

    @Autowired
    private LRService lrService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnMessage login(@RequestBody LoginVO loginVO, HttpServletRequest httpServletRequest){
        ReturnMessage returnMessage=lrService.login(loginVO);
        return returnMessage;
    }
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ReturnMessage register(@RequestBody RegisterVO registerVO, HttpServletRequest httpServletRequest){
        ReturnMessage returnMessage=lrService.register(registerVO);
        return returnMessage;
    }
}
