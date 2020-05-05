package com.wedding.usermanage.controller;


import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.LRService;
import com.wedding.usermanage.utils.MsgUtil;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.RegisterVO;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/")
@Controller
public class LRController {

    @Autowired
    private LRService lrService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnMessage login(@RequestBody LoginVO loginVO, HttpServletRequest httpServletRequest){
        LoginVO currentLoginVO=lrService.login(loginVO);
        if(currentLoginVO.getUserid()!=0){
            HttpSession session=httpServletRequest.getSession(true);
            session.setAttribute("userinfo",currentLoginVO);
            return new ReturnMessage(true,"ok");
        }
        return new ReturnMessage(false,"there is something wrong!");
    }
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ReturnMessage register(@RequestBody RegisterVO registerVO, HttpServletRequest httpServletRequest){
        ReturnMessage returnMessage=lrService.register(registerVO);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/register/sendValidateNumber",method = RequestMethod.POST)
    public ReturnMessage register(@RequestBody String phone, HttpServletRequest httpServletRequest){
        MsgUtil.sendMsg(phone,httpServletRequest);
        return new ReturnMessage(true,"ok");
    }

    @ResponseBody
    @RequestMapping(value = "/getCurrentUser",method = RequestMethod.GET)
    public ReturnMessage register(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,loginVO);
        }
        return new ReturnMessage(false,null);
    }

    @ResponseBody
    @RequestMapping(value = "/quitLogin",method = RequestMethod.GET)
    public ReturnMessage quitLogin(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            session.removeAttribute("userinfo");
            return new ReturnMessage(true,"注销成功");
        }
        return new ReturnMessage(false,"当前没有登录用户");
    }
}
