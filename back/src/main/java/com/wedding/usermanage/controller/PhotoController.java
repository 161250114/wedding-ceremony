package com.wedding.usermanage.controller;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.PhotoService;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.PhotoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("/photo")
@Controller
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ReturnMessage uploadFile(@RequestParam Map<String,String> map, @RequestParam("file") MultipartFile[] file,HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return photoService.uploadPhotos(loginVO.getUserid(),file);
        }
        return new ReturnMessage(false,"尚未登录！");
    }

    @ResponseBody
    @RequestMapping(value = "/getAlbum",method = RequestMethod.GET)
    public ReturnMessage getAlbum(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            LoginVO loginVO=(LoginVO) session.getAttribute("userinfo");
            return new ReturnMessage(true,photoService.getAlbum(loginVO.getUserid()));
        }
        return new ReturnMessage(false,"尚未登录！");
    }

    @ResponseBody
    @RequestMapping(value = "/changePhotoOrder",method = RequestMethod.POST)
    public ReturnMessage changePhotoOrder(@RequestBody PhotoVO photoVO, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            return photoService.changePhotoOrder(photoVO);
        }
        return new ReturnMessage(false,"尚未登录！");
    }

    @ResponseBody
    @RequestMapping(value = "/deletePhoto",method = RequestMethod.POST)
    public ReturnMessage deletePhote(@RequestBody PhotoVO photoVO, HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession(false);
        if(session!=null){
            return photoService.deletePhoto(photoVO);
        }
        return new ReturnMessage(false,"尚未登录！");
    }
}
