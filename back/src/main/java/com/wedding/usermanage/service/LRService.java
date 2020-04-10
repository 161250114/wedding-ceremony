package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.RegisterVO;
import com.wedding.usermanage.vo.UserVO;
import org.springframework.stereotype.Service;

@Service("testService")
public interface LRService {
    public ReturnMessage login(LoginVO loginVO);

    public ReturnMessage register(RegisterVO registerVO);

    public ReturnMessage validate(String phone);

}

