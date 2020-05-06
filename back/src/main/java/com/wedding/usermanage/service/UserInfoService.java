package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.BaseInfoVO;
import com.wedding.usermanage.vo.IntroductionVO;
import com.wedding.usermanage.vo.StandardVO;
import com.wedding.usermanage.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    public ReturnMessage getUserStatus(int userid);
    public BaseInfoVO getBaseInfo(int userid);
    public ReturnMessage changeBaseInfo(int userid, BaseInfoVO baseInfoVO);
    public StandardVO getStandard(int userid);
    public ReturnMessage changeStandard(int userid,StandardVO standardVO);
    public IntroductionVO getIntroduction(int userid);
    public ReturnMessage changeIntroduction(int userid,IntroductionVO introductionVO);

}
