package com.wedding.usermanage.controller;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.RecordService;
import com.wedding.usermanage.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/record")
@Controller
public class RecordController {

    @Autowired
    private RecordService recordService;

    @ResponseBody
    @RequestMapping(value = "/getBillRecords",method = RequestMethod.GET)
    public ReturnMessage getBillRecords(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,recordService.getBillRecords(loginVO.getUserid()));
        }
        return new ReturnMessage(false,"尚未登录");
    }

    @ResponseBody
    @RequestMapping(value = "/getDateRecords",method = RequestMethod.GET)
    public ReturnMessage getDateRecords(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,recordService.getDateRecords(loginVO.getUserid()));
        }
        return new ReturnMessage(false,"尚未登录");
    }

}
