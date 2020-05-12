package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.*;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    ReturnMessage getUserStatus(int userid);
    BaseInfoVO getBaseInfo(int userid);
    ReturnMessage changeBaseInfo(int userid, BaseInfoVO baseInfoVO);
    StandardVO getStandard(int userid);
    ReturnMessage changeStandard(int userid,StandardVO standardVO);
    IntroductionVO getIntroduction(int userid);
    ReturnMessage changeIntroduction(int userid,IntroductionVO introductionVO);
    ReturnMessage changePassword(int userid, PasswordVO passwordVO);
    ReturnMessage changePhone(int userid,String newPhone);
    String getPhone(int userid);

}
