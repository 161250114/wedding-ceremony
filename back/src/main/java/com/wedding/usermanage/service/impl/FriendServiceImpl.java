package com.wedding.usermanage.service.impl;

import com.wedding.mapper.User_relationMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.User_relation;
import com.wedding.usermanage.service.FriendService;
import com.wedding.usermanage.vo.FriendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("friendService")
public class FriendServiceImpl implements FriendService {
    @Autowired
    private User_relationMapper user_relationMapper;

    @Override
    public FriendVO[] getFriendList(int userid) {
        List<User_relation> user_relations=user_relationMapper.selectByUserId1(userid);
        FriendVO[] friendVOS=new FriendVO[user_relations.size()];
        for(int i=0;i<user_relations.size();i++){
            FriendVO friendVO=new FriendVO();
            friendVO.setRemark(user_relations.get(i).getRemark());
            friendVO.setType(user_relations.get(i).getType());
            friendVO.setUserid2(user_relations.get(i).getUserid2());
            friendVOS[i]=friendVO;
        }
        return friendVOS;
    }

    @Override
    public ReturnMessage changeRemark(int userid, FriendVO friendVO) {
        User_relation user_relation=user_relationMapper.selectByUserId1and2(userid,friendVO.getUserid2());
        user_relation.setRemark(friendVO.getRemark());
        user_relationMapper.updateByPrimaryKey(user_relation);
        return new ReturnMessage(true,"OK!");
    }

    @Override
    public ReturnMessage deleteFriend(int userid, FriendVO friendVO) {
        User_relation user_relation=user_relationMapper.selectByUserId1and2(userid,friendVO.getUserid2());
        user_relationMapper.deleteByPrimaryKey(user_relation.getId());
        return new ReturnMessage(true,"OK!");
    }
}
