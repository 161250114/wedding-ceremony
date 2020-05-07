package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.FriendVO;

public interface FriendService {
    FriendVO[] getFriendList(int userid);
    ReturnMessage changeRemark(int userid,FriendVO friendVO);
    ReturnMessage deleteFriend(int userid,FriendVO friendVO);
}
