package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.RegisterVO;
import org.springframework.stereotype.Service;

@Service
public interface LRService {
    ReturnMessage login(LoginVO loginVO);

    ReturnMessage register(RegisterVO registerVO);

    ReturnMessage checkUsername(String username);

}

