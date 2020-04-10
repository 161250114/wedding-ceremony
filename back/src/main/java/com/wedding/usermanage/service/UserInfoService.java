package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.UserVO;

public interface UserInfoService {

    public ReturnMessage addUserDetails(UserVO userVO);
    public ReturnMessage changeUserDetails(UserVO userVO);
    public ReturnMessage deleteUser(UserVO userVO);

}
