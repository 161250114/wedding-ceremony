package com.wedding.usermanage.service.impl;

import com.wedding.mapper.*;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.*;
import com.wedding.usermanage.service.FriendService;
import com.wedding.usermanage.vo.*;
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
    @Autowired
    private Date_applyMapper date_applyMapper;
    @Autowired
    private Date_recordMapper date_recordMapper;

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
    public FriendApplyVO[] getSendFriendApplyList(int userid) {
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
    public FriendApplyVO[] getReceiveFriendApplyList(int userid) {
        List<Friend_apply> friend_applies=friend_applyMapper.selectByUserid2(userid);
        int size=friend_applies.size();
        for(int i=0;i<friend_applies.size();i++){
            Friend_apply friend_apply=friend_applies.get(i);
            if(friend_apply.getResult().equals("已撤销")){
                size--;
            }
        }
        FriendApplyVO[] friendApplyVOS=new FriendApplyVO[size];
        int arrayIndex=0;
        for(int i=0;i<friend_applies.size();i++){
            FriendApplyVO friendApplyVO=new FriendApplyVO();
            Friend_apply friend_apply=friend_applies.get(i);
            if(friend_apply.getResult().equals("已撤销")){
                continue;
            }
            friendApplyVO.setUserid1(friend_apply.getUserid1());
            friendApplyVO.setId(friend_apply.getId());
            friendApplyVO.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").format(friend_apply.getApplydate()));
            friendApplyVO.setApplyInfo(friend_apply.getApplyinfo());
            friendApplyVO.setReplyInfo(friend_apply.getReplyinfo());
            friendApplyVO.setResult(friend_apply.getResult());
            friendApplyVO.setUserid2(friend_apply.getUserid2());
            friendApplyVOS[arrayIndex]=friendApplyVO;
            arrayIndex++;
        }
        return friendApplyVOS;
    }

    @Override
    public ReturnMessage handleFriendApply(FriendApplyVO friendApplyVO) {
        Friend_apply friend_apply=friend_applyMapper.selectByPrimaryKey(friendApplyVO.getId());
        friend_apply.setResult(friendApplyVO.getResult());
        friend_apply.setReplyinfo(friendApplyVO.getReplyInfo());
        friend_applyMapper.updateByPrimaryKey(friend_apply);
        //如果通过好友申请，就在用户间建立对应关系，备注名默认为用户名
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

    @Override
    public ReturnMessage sendDateApply(DateApplyVO dateApplyVO) {
        User user=userMapper.selectByPrimaryKey(dateApplyVO.getUserid1());
        if(user.getDateStatus()==1){
            return new ReturnMessage(false,"你已经有约会对象啦！请勿花心！！");
        }
        List<Date_apply> date_applies=date_applyMapper.selectByUserid1(dateApplyVO.getUserid1());
        for(int i=0;i<date_applies.size();i++){
            Date_apply date_apply=date_applies.get(i);
            if(date_apply.getUserid2()==dateApplyVO.getUserid2()&&date_apply.getResult().equals("等待")){
                return new ReturnMessage(false,"您已向该用户发送过申请啦！请耐心等待~~");
            }
        }
        Date_apply date_apply=new Date_apply();
        date_apply.setApplydate(new Date());
        date_apply.setApplyinfo(dateApplyVO.getApplyInfo());
        date_apply.setReplyinfo("暂无");
        date_apply.setResult("等待");
        date_apply.setUserid1(dateApplyVO.getUserid1());
        date_apply.setUserid2(dateApplyVO.getUserid2());
        date_applyMapper.insert(date_apply);
        return new ReturnMessage(true,"发送成功!");
    }

    @Override
    public DateApplyVO[] getSendDateApplyList(int userid) {
        List<Date_apply> date_applies=date_applyMapper.selectByUserid1(userid);
        DateApplyVO[] dateApplyVOS=new DateApplyVO[date_applies.size()];
        for(int i=0;i<date_applies.size();i++){
            DateApplyVO dateApplyVO=new DateApplyVO();
            Date_apply date_apply=date_applies.get(i);
            dateApplyVO.setUserid1(date_apply.getUserid1());
            dateApplyVO.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").format(date_apply.getApplydate()));
            dateApplyVO.setApplyInfo(date_apply.getApplyinfo());
            dateApplyVO.setReplyInfo(date_apply.getReplyinfo());
            dateApplyVO.setId(date_apply.getId());
            dateApplyVO.setResult(date_apply.getResult());
            dateApplyVO.setUserid2(date_apply.getUserid2());
            dateApplyVOS[i]=dateApplyVO;
        }
        return dateApplyVOS;
    }

    @Override
    public DateApplyVO[] getReceiveDateApplyList(int userid) {
        List<Date_apply> date_applies=date_applyMapper.selectByUserid2(userid);
        int size=date_applies.size();
        for(int i=0;i<date_applies.size();i++){
            Date_apply date_apply=date_applies.get(i);
            if(date_apply.getResult().equals("已撤销")){
                size--;
            }
        }
        DateApplyVO[] dateApplyVOS=new DateApplyVO[size];
        int arrayIndex=0;
        for(int i=0;i<date_applies.size();i++){
            DateApplyVO dateApplyVO=new DateApplyVO();
            Date_apply date_apply=date_applies.get(i);
            if(date_apply.getResult().equals("已撤销")){
                continue;
            }
            dateApplyVO.setUserid1(date_apply.getUserid1());
            dateApplyVO.setId(date_apply.getId());
            dateApplyVO.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").format(date_apply.getApplydate()));
            dateApplyVO.setApplyInfo(date_apply.getApplyinfo());
            dateApplyVO.setReplyInfo(date_apply.getReplyinfo());
            dateApplyVO.setResult(date_apply.getResult());
            dateApplyVO.setUserid2(date_apply.getUserid2());
            dateApplyVOS[arrayIndex]=dateApplyVO;
            arrayIndex++;
        }
        return dateApplyVOS;
    }

    @Override
    public ReturnMessage handleDateApply(DateApplyVO dateApplyVO) {
        Date_apply date_apply=date_applyMapper.selectByPrimaryKey(dateApplyVO.getId());
        if(date_apply.getResult().equals("已撤销")){
            return new ReturnMessage(false,"该申请已被撤销！");//如果已被撤销就不做更改
        }
        date_apply.setResult(dateApplyVO.getResult());
        date_apply.setReplyinfo(dateApplyVO.getReplyInfo());
        date_applyMapper.updateByPrimaryKey(date_apply);
        if(dateApplyVO.getResult().equals("通过")){
            //建立约会关系
            Date_record date_record=new Date_record();
            date_record.setStartDate(new Date());
            date_record.setUserid1(date_apply.getUserid1());
            date_record.setUserid2(date_apply.getUserid2());
            date_record.setEndDate(null);
            date_recordMapper.insert(date_record);

            User user1=userMapper.selectByPrimaryKey(date_apply.getUserid1());
            user1.setDateStatus((byte)1);
            userMapper.updateByPrimaryKey(user1);

            User user2=userMapper.selectByPrimaryKey(date_apply.getUserid2());
            user2.setDateStatus((byte)1);
            userMapper.updateByPrimaryKey(user2);
        }
        return new ReturnMessage(true,"处理成功");
    }
}
