package com.wedding.usermanage.controller;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.BuyService;
import com.wedding.usermanage.vo.IntroductionVO;
import com.wedding.usermanage.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/buy")
@Controller
public class BuyController {

    @Autowired
    private BuyService buyService;

    @ResponseBody
    @RequestMapping(value = "/chargeMoney",method = RequestMethod.POST)
    public ReturnMessage chargeMoney(@RequestBody double money, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            if(buyService.chargeMoney(loginVO.getUserid(),money).isResult()){
                return new ReturnMessage(true,"充值成功");
            }
        }
        return new ReturnMessage(false,"尚未登录");
    }
    @ResponseBody
    @RequestMapping(value = "/buyVip",method = RequestMethod.POST)
    public ReturnMessage buyVip(@RequestBody int vipDays, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return buyService.buyVIP(loginVO.getUserid(),vipDays);
        }
        return new ReturnMessage(false,"尚未登录");
    }
    @ResponseBody
    @RequestMapping(value = "/buyAlbumMax",method = RequestMethod.POST)
    public ReturnMessage buyAlbumMax(@RequestBody int num, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return buyService.buyAlbumMax(loginVO.getUserid(),num);
        }
        return new ReturnMessage(false,"尚未登录");
    }

    @ResponseBody
    @RequestMapping(value = "/getAlbumMax",method = RequestMethod.GET)
    public ReturnMessage getAlbumMax(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return buyService.getAlbumMax(loginVO.getUserid());
        }
        return new ReturnMessage(false,"尚未登录");
    }
}
