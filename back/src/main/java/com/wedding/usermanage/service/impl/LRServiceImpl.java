package com.wedding.usermanage.service.impl;

import com.wedding.mapper.UserMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.service.LRService;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.RegisterVO;
import com.wedding.usermanage.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class LRServiceImpl implements LRService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnMessage login(LoginVO loginVO) {
        return null;
    }

    @Override
    public ReturnMessage register(RegisterVO registerVO) {
        return null;
    }

    @Override
    public ReturnMessage validate(String phone) {
        return null;
    }
}