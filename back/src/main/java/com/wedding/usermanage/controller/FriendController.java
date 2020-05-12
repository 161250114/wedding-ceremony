package com.wedding.usermanage.controller;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.FriendService;
import com.wedding.usermanage.vo.FriendApplyVO;
import com.wedding.usermanage.vo.FriendVO;
import com.wedding.usermanage.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/friend")
@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;

    @ResponseBody
    @RequestMapping(value = "/getFriendList",method = RequestMethod.GET)
    public ReturnMessage getFriendList(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,friendService.getFriendList(loginVO.getUserid()));
        }
        return new ReturnMessage(false,"尚未登录!");
    }

    @ResponseBody
    @RequestMapping(value = "/changeRemark",method = RequestMethod.POST)
    public ReturnMessage changeRemark(@RequestBody FriendVO friendVO, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return friendService.changeRemark(loginVO.getUserid(),friendVO);
        }
        return new ReturnMessage(false,"尚未登录");
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFriend",method = RequestMethod.POST)
    public ReturnMessage deleteFriend(@RequestBody FriendVO friendVO, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return friendService.deleteFriend(loginVO.getUserid(),friendVO);
        }
        return new ReturnMessage(false,"尚未登录");
    }

    @ResponseBody
    @RequestMapping(value = "/fuzzySearchUserByUsername",method = RequestMethod.POST)
    public ReturnMessage fuzzySearchUserByUsername(@RequestBody String username, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,friendService.fuzzySearchUserByUsername(username));
        }
        return new ReturnMessage(false,"尚未登录");
    }

    @ResponseBody
    @RequestMapping(value = "/searchUserByUserid",method = RequestMethod.POST)
    public ReturnMessage fsearchUserByUserid(@RequestBody String userid, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,friendService.searchUserByUserid(userid));
        }
        return new ReturnMessage(false,"尚未登录");
    }

    @ResponseBody
    @RequestMapping(value = "/sendFriendApply",method = RequestMethod.POST)
    public ReturnMessage fsearchUserByUserid(@RequestBody FriendApplyVO friendApplyVO, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            friendApplyVO.setUserid1(loginVO.getUserid());
            return friendService.sendFriendApply(friendApplyVO);
        }
        return new ReturnMessage(false,"尚未登录");
    }

    @ResponseBody
    @RequestMapping(value = "/getSendApplyList",method = RequestMethod.GET)
    public ReturnMessage getSendApplyList(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,friendService.getSendApplyList(loginVO.getUserid()));
        }
        return new ReturnMessage(false,"尚未登录！");
    }

    @ResponseBody
    @RequestMapping(value = "/getReceiveApplyList",method = RequestMethod.GET)
    public ReturnMessage getReceiveApplyList(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,friendService.getReceiveApplyList(loginVO.getUserid()));
        }
        return new ReturnMessage(false,"尚未登录！");
    }

    @ResponseBody
    @RequestMapping(value = "/handleFriendApply",method = RequestMethod.POST)
    public ReturnMessage handleFriendApply(@RequestBody FriendApplyVO friendApplyVO,HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            return friendService.handleApply(friendApplyVO);
        }
        return new ReturnMessage(false,"尚未登录！");
    }
}
