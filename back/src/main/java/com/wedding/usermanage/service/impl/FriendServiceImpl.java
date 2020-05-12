package com.wedding.usermanage.service.impl;

import com.wedding.mapper.Friend_applyMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.mapper.User_relationMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.Friend_apply;
import com.wedding.model.po.User;
import com.wedding.model.po.User_relation;
import com.wedding.usermanage.service.FriendService;
import com.wedding.usermanage.vo.FriendApplyVO;
import com.wedding.usermanage.vo.FriendVO;
import com.wedding.usermanage.vo.IntroductionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("friendService")
public class FriendServiceImpl implements FriendService {
    @Autowired
    private User_relationMapper user_relationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Friend_applyMapper friend_applyMapper;

    @Override
    public FriendVO[] getFriendList(int userid) {
        List<User_relation> user_relations=user_relationMapper.selectByUserId1(userid);
        FriendVO[] friendVOS=new FriendVO[user_relations.size()];
        for(int i=0;i<user_relations.size();i++){
            FriendVO friendVO=new FriendVO();
            friendVO.setRemark(user_relations.get(i).getRemark());
            friendVO.setType(user_relations.get(i).getType());
            friendVO.setUserid(user_relations.get(i).getUserid2());
            User user=userMapper.selectByPrimaryKey(friendVO.getUserid());
            friendVO.setUsername(user.getUsername());
            friendVOS[i]=friendVO;
        }
        return friendVOS;
    }

    @Override
    public ReturnMessage changeRemark(int userid, FriendVO friendVO) {
        User_relation user_relation=user_relationMapper.selectByUserId1and2(userid,friendVO.getUserid());
        user_relation.setRemark(friendVO.getRemark());
        user_relationMapper.updateByPrimaryKey(user_relation);
        return new ReturnMessage(true,"修改成功！");
    }

    @Override
    public ReturnMessage deleteFriend(int userid, FriendVO friendVO) {
        User_relation user_relation1=user_relationMapper.selectByUserId1and2(userid,friendVO.getUserid());
        User_relation user_relation2=user_relationMapper.selectByUserId1and2(friendVO.getUserid(),userid);
        user_relationMapper.deleteByPrimaryKey(user_relation1.getId());
        user_relationMapper.deleteByPrimaryKey(user_relation2.getId());
        return new ReturnMessage(true,"删除成功！");
    }

    @Override
    public FriendVO[] fuzzySearchUserByUsername(String username) {
        List<User> users=userMapper.fuzzySelectByUsername(username);
        FriendVO[] friendVOS=new FriendVO[users.size()];
        for(int i=0;i<users.size();i++){
            User user=users.get(i);
            FriendVO friendVO=new FriendVO();
            friendVO.setUserid(user.getId());
            friendVO.setUsername(user.getUsername());
            friendVOS[i]=friendVO;
        }
        return friendVOS;
    }

    @Override
    public FriendVO[] searchUserByUserid(String userid) {
        int tempuserid= Integer.parseInt(userid);
        User user=userMapper.selectByPrimaryKey(tempuserid);
        FriendVO[] friendVOS=new FriendVO[1];
        FriendVO friendVO=new FriendVO();
        friendVO.setUsername(user.getUsername());
        friendVO.setUserid(user.getId());
        friendVOS[0]=friendVO;
        return friendVOS;
    }

    @Override
    public ReturnMessage sendFriendApply(FriendApplyVO friendApplyVO) {
        Friend_apply friend_apply=new Friend_apply();
        friend_apply.setApplydate(new Date());
        friend_apply.setApplyinfo(friendApplyVO.getApplyInfo());
        friend_apply.setReplyinfo("暂无");
        friend_apply.setResult("等待");
        friend_apply.setUserid1(friendApplyVO.getUserid1());
        friend_apply.setUserid2(friendApplyVO.getUserid2());
        friend_applyMapper.insert(friend_apply);
        return new ReturnMessage(true,"发送成功");
    }

    @Override
    public FriendApplyVO[] getSendApplyList(int userid) {
        List<Friend_apply> friend_applies=friend_applyMapper.selectByUserid1(userid);
        FriendApplyVO[] friendApplyVOS=new FriendApplyVO[friend_applies.size()];
        for(int i=0;i<friend_applies.size();i++){
            FriendApplyVO friendApplyVO=new FriendApplyVO();
            Friend_apply friend_apply=friend_applies.get(i);
            friendApplyVO.setUserid1(friend_apply.getUserid1());
            friendApplyVO.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").format(friend_apply.getApplydate()));
            friendApplyVO.setApplyInfo(friend_apply.getApplyinfo());
            friendApplyVO.setReplyInfo(friend_apply.getReplyinfo());
            friendApplyVO.setId(friend_apply.getId());
            friendApplyVO.setResult(friend_apply.getResult());
            friendApplyVO.setUserid2(friend_apply.getUserid2());
            friendApplyVOS[i]=friendApplyVO;
        }
        return friendApplyVOS;
    }

    @Override
    public FriendApplyVO[] getReceiveApplyList(int userid) {
        List<Friend_apply> friend_applies=friend_applyMapper.selectByUserid2(userid);
        FriendApplyVO[] friendApplyVOS=new FriendApplyVO[friend_applies.size()];
        for(int i=0;i<friend_applies.size();i++){
            FriendApplyVO friendApplyVO=new FriendApplyVO();
            Friend_apply friend_apply=friend_applies.get(i);
            friendApplyVO.setUserid1(friend_apply.getUserid1());
            friendApplyVO.setId(friend_apply.getId());
            friendApplyVO.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").format(friend_apply.getApplydate()));
            friendApplyVO.setApplyInfo(friend_apply.getApplyinfo());
            friendApplyVO.setReplyInfo(friend_apply.getReplyinfo());
            friendApplyVO.setResult(friend_apply.getResult());
            friendApplyVO.setUserid2(friend_apply.getUserid2());
            friendApplyVOS[i]=friendApplyVO;
        }
        return friendApplyVOS;
    }

    @Override
    public ReturnMessage handleApply(FriendApplyVO friendApplyVO) {
        Friend_apply friend_apply=friend_applyMapper.selectByPrimaryKey(friendApplyVO.getId());
        friend_apply.setResult(friendApplyVO.getResult());
        friend_apply.setReplyinfo(friendApplyVO.getReplyInfo());
        friend_applyMapper.updateByPrimaryKey(friend_apply);
        if(friendApplyVO.getResult().equals("通过")){
            User_relation user_relation1=new User_relation();
            user_relation1.setUserid1(friend_apply.getUserid1());
            user_relation1.setUserid2(friend_apply.getUserid2());
            User user2=userMapper.selectByPrimaryKey(friend_apply.getUserid2());
            user_relation1.setRemark(user2.getUsername());
            user_relation1.setType((byte)1);
            user_relationMapper.insert(user_relation1);

            User_relation user_relation2=new User_relation();
            user_relation2.setUserid1(friend_apply.getUserid2());
            user_relation2.setUserid2(friend_apply.getUserid1());
            User user1=userMapper.selectByPrimaryKey(friend_apply.getUserid1());
            user_relation2.setRemark(user1.getUsername());
            user_relation2.setType((byte)1);
            user_relationMapper.insert(user_relation2);

        }
        return new ReturnMessage(true,"处理成功");
    }
}
