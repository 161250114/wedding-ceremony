package com.wedding.usermanage.controller;


import com.github.qcloudsms.SmsSingleSenderResult;
import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.LRService;
import com.wedding.usermanage.service.UserInfoService;
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
    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnMessage login(@RequestBody LoginVO loginVO, HttpServletRequest httpServletRequest){
        ReturnMessage loginResult=lrService.login(loginVO);
        if(loginResult.isResult()){
            HttpSession session=httpServletRequest.getSession(true);
            session.setAttribute("userinfo",loginResult.getMessage());
            return new ReturnMessage(true,"登录成功！");
        }
        return loginResult;
    }
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ReturnMessage register(@RequestBody RegisterVO registerVO, HttpServletRequest httpServletRequest){
        ReturnMessage returnMessage=lrService.register(registerVO);
        return returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/sendValidateNumber",method = RequestMethod.POST)
    public ReturnMessage register(@RequestBody String phone, HttpServletRequest httpServletRequest){
        if(phone.substring(3,7).equals("****")){
            HttpSession session=httpServletRequest.getSession(false);
            if(session!=null) {
                LoginVO loginVO = (LoginVO) session.getAttribute("userinfo");
                MsgUtil.sendMsg(userInfoService.getPhone(loginVO.getUserid()),httpServletRequest);
            }
        }else{
            MsgUtil.sendMsg(phone,httpServletRequest);
        }
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
