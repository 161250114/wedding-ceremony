package com.wedding.UserManage.controller;


import com.wedding.UserManage.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/")
    public String test(){
        System.out.println("OK");
        testService.test();
        return "index";
    }
}
