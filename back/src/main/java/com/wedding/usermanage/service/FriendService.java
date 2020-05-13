package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.DateApplyVO;
import com.wedding.usermanage.vo.FriendApplyVO;
import com.wedding.usermanage.vo.FriendVO;

public interface FriendService {
    FriendVO[] getFriendList(int userid);
    ReturnMessage changeRemark(int userid,FriendVO friendVO);
    ReturnMessage deleteFriend(int userid,FriendVO friendVO);
    FriendVO[] fuzzySearchUserByUsername(String username);
    FriendVO[] searchUserByUserid(String userid);
    ReturnMessage sendFriendApply(FriendApplyVO friendApplyVO);
    FriendApplyVO[] getSendFriendApplyList(int userid);
    FriendApplyVO[] getReceiveFriendApplyList(int userid);
    ReturnMessage handleFriendApply(FriendApplyVO friendApplyVO);
    ReturnMessage sendDateApply(DateApplyVO dateApplyVO);
    DateApplyVO[] getSendDateApplyList(int userid);
    DateApplyVO[] getReceiveDateApplyList(int userid);
    ReturnMessage handleDateApply(DateApplyVO dateApplyVO);

}
