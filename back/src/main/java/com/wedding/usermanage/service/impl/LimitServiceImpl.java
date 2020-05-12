package com.wedding.usermanage.service.impl;

import com.wedding.mapper.User_limitMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.User_limit;
import com.wedding.usermanage.service.LimitService;
import com.wedding.usermanage.vo.UserLimitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("limitService")
public class LimitServiceImpl implements LimitService {

    @Autowired
    User_limitMapper user_limitMapper;

    @Override
    public ReturnMessage getUserLimit(int userid) {
        User_limit user_limit=user_limitMapper.selectByUserId(userid);
        UserLimitVO userLimitVO=new UserLimitVO();
        userLimitVO.setLimitBrowse(user_limit.getLimitBrowse());
        userLimitVO.setLimitDapply(user_limit.getLimitDapply());
        userLimitVO.setLimitFapply(user_limit.getLimitFapply());
        userLimitVO.setLimitMessage(user_limit.getLimitMessage());
        return new ReturnMessage(true,userLimitVO);
    }

    @Override
    public ReturnMessage changeUserLimit(int userid, UserLimitVO userLimitVO) {
        User_limit user_limit=user_limitMapper.selectByUserId(userid);
        user_limit.setLimitMessage((byte)userLimitVO.getLimitMessage());
        user_limit.setLimitFapply((byte)userLimitVO.getLimitFapply());
        user_limit.setLimitDapply((byte)userLimitVO.getLimitDapply());
        user_limit.setLimitBrowse((byte)userLimitVO.getLimitBrowse());
        user_limitMapper.updateByPrimaryKey(user_limit);
        return new ReturnMessage(true,"修改成功");
    }
}
