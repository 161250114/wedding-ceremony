package com.wedding.usermanage.controller;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.UserCreditService;
import com.wedding.usermanage.vo.CreditCommentVO;
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

@RequestMapping("/credit")
@Controller
public class UserCreditController {

    @Autowired
    private UserCreditService userCreditService;

    @ResponseBody
    @RequestMapping(value = "/addCreditComment",method = RequestMethod.POST)
    public ReturnMessage addCreditComment(@RequestBody CreditCommentVO creditCommentVO, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            creditCommentVO.setUserid1(loginVO.getUserid());
            return userCreditService.addCreditComment(creditCommentVO);
        }
        return new ReturnMessage(false,"尚未登录");
    }
}
