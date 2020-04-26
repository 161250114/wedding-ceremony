package com.wedding.usermanage.controller;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.LoginVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/")
@Controller
public class PhotoController {

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ReturnMessage uploadFile(@RequestParam Map<String,String> map, @RequestParam("file") MultipartFile[] file){
        if(map.size() >0){
            try{
                String  fileId = map.get("fileId").toString();
                String  fileType = map.get("fileType").toString();
                String  fileDesc = map.get("fileDesc").toString();
                String  filePath = map.get("filePath").toString();
            }catch (Exception e){
                return new ReturnMessage();
            }
            return new ReturnMessage();
        }
        return new ReturnMessage();
    }
}
