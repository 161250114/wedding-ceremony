package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.UserLimitVO;

public interface LimitService {
    ReturnMessage getUserLimit(int userid);
    ReturnMessage changeUserLimit(int userid, UserLimitVO userLimitVO);
}
