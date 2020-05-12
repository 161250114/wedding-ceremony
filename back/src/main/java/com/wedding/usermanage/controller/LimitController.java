package com.wedding.usermanage.controller;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.LimitService;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.UserLimitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/limit")
@Controller
public class LimitController {

    @Autowired
    private LimitService limitService;

    @ResponseBody
    @RequestMapping(value = "/getUserLimits",method = RequestMethod.GET)
    public ReturnMessage getUserLimits(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return limitService.getUserLimit(loginVO.getUserid());
        }
        return new ReturnMessage(false,"尚未登录");
    }

    @ResponseBody
    @RequestMapping(value = "/changeUserLimits",method = RequestMethod.POST)
    public ReturnMessage buyAlbumMax(@RequestBody UserLimitVO userLimitVO, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return limitService.changeUserLimit(loginVO.getUserid(),userLimitVO);
        }
        return new ReturnMessage(false,"尚未登录");
    }

}
