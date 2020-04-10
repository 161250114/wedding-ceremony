package com.wedding.usermanage.controller;


import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.LRService;
import com.wedding.usermanage.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class LoginController {

    @Autowired
    private LRService LRService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnMessage login(@RequestBody LoginVO loginVO, HttpServletRequest httpServletRequest){
        ReturnMessage returnMessage=LRService.login();
        return returnMessage;
    }
}
