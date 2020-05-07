package com.wedding.usermanage.service.impl;

import com.wedding.mapper.Date_standardMapper;
import com.wedding.mapper.UserLabelMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.mapper.UserQuestionMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.Date_standard;
import com.wedding.model.po.User;
import com.wedding.model.po.UserLabel;
import com.wedding.model.po.UserQuestion;
import com.wedding.usermanage.service.UserInfoService;
import com.wedding.usermanage.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Date_standardMapper date_standardMapper;
    @Autowired
    private UserLabelMapper userLabelMapper;
    @Autowired
    private UserQuestionMapper userQuestionMapper;

    @Override
    public ReturnMessage getUserStatus(int userid) {
        User user=userMapper.selectByPrimaryKey(userid);
        UserStatusVO userStatusVO=new UserStatusVO();
        userStatusVO.setBalance(user.getBalance());
        userStatusVO.setDateStatus(user.getDateStatus());
        userStatusVO.setUserid(userid);
        userStatusVO.setUsername(user.getUsername());
        userStatusVO.setUsertype(user.getUsertype());
        return new ReturnMessage(true,userStatusVO);
    }

    @Override
    public BaseInfoVO getBaseInfo(int userid) {
        User user = userMapper.selectByPrimaryKey(userid);
        BaseInfoVO baseInfoVO=new BaseInfoVO();
        baseInfoVO.setAddress(user.getAddress());
        DateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        baseInfoVO.setBirthday(format1.format(user.getBirthday()));
        baseInfoVO.setEducation(user.getEducation());
        baseInfoVO.setFullname(user.getFullname());
        baseInfoVO.setHeight(user.getHeight());
        baseInfoVO.setMarriage(user.getMarrige());
        baseInfoVO.setPhone(user.getPhone());
        baseInfoVO.setProfession(user.getProfession());
        baseInfoVO.setSalary(user.getSalary());
        baseInfoVO.setSex(user.getSex());
        baseInfoVO.setUsername(user.getUsername());
        return baseInfoVO;
    }

    @Override
    public ReturnMessage changeBaseInfo(int userid, BaseInfoVO baseInfoVO) {
        User user=userMapper.selectByPrimaryKey(userid);
        user.setUsername(baseInfoVO.getUsername());
        user.setPhone(baseInfoVO.getPhone());
        user.setAddress(baseInfoVO.getAddress());
        user.setEducation(baseInfoVO.getEducation());
        user.setMarrige((byte)baseInfoVO.getMarriage());
        user.setProfession(baseInfoVO.getProfession());
        user.setSalary(baseInfoVO.getSalary());
        userMapper.updateByPrimaryKey(user);
        return new ReturnMessage(true,"OK!");
    }

    @Override
    public StandardVO getStandard(int userid) {
        Date_standard date_standard=date_standardMapper.selectByUserId(userid);
        StandardVO standardVO=new StandardVO();
        standardVO.setAddress(date_standard.getAddress());
        standardVO.setAgeMax(date_standard.getAgemax());
        standardVO.setAgeMin(date_standard.getAgemin());
        standardVO.setEducation(date_standard.getEducation());
        standardVO.setHeightMax(date_standard.getHeightmax());
        standardVO.setHeightMin(date_standard.getHeightmin());
        standardVO.setMarriage(date_standard.getMarrige());
        standardVO.setSalary(date_standard.getSalary());
        return standardVO;
    }

    @Override
    public ReturnMessage changeStandard(int userid, StandardVO standardVO) {
        Date_standard date_standard=date_standardMapper.selectByUserId(userid);
        date_standard.setSalary(standardVO.getSalary());
        date_standard.setMarrige((byte)standardVO.getMarriage());
        date_standard.setEducation(standardVO.getEducation());
        date_standard.setAddress(standardVO.getAddress());
        date_standard.setHeightmax(standardVO.getHeightMax());
        date_standard.setHeightmin(standardVO.getHeightMin());
        date_standard.setAgemax(standardVO.getAgeMax());
        date_standard.setAgemin(standardVO.getAgeMin());
        date_standardMapper.updateByPrimaryKey(date_standard);
        return new ReturnMessage(true,"ok");
    }

    @Override
    public IntroductionVO getIntroduction(int userid) {
        User user=userMapper.selectByPrimaryKey(userid);
        IntroductionVO introductionVO=new IntroductionVO();
        introductionVO.setIntroduction(user.getIntroduction());
        List<UserLabel> userLabels=userLabelMapper.selectByUserId(userid);
        String[] tagList=new String[userLabels.size()];
        for(int i=0;i<userLabels.size();i++){
            tagList[i]=userLabels.get(i).getLabel();
        }
        introductionVO.setTagList(tagList);
        List<UserQuestion> userQuestions=userQuestionMapper.selectByUserId(userid);
        QuestionVO[] questionList=new QuestionVO[userQuestions.size()];
        for(int i=0;i<userQuestions.size();i++){
            QuestionVO questionVO=new QuestionVO();
            questionVO.setQuestionid(userQuestions.get(i).getQuestionid());
            questionVO.setAnswer(userQuestions.get(i).getAnswer());
            questionList[i]=questionVO;
        }
        introductionVO.setQuestionList(questionList);
        return introductionVO;

    }

    @Override
    public ReturnMessage changeIntroduction(int userid, IntroductionVO introductionVO) {
        User user=userMapper.selectByPrimaryKey(userid);
        user.setIntroduction(introductionVO.getIntroduction());
        userMapper.updateByPrimaryKey(user);
        userLabelMapper.deleteByUserId(userid);
        for(int i=0;i<introductionVO.getTagList().length;i++){
            UserLabel userLabel=new UserLabel();
            userLabel.setUserId(user.getId());
            userLabel.setLabel(introductionVO.getTagList()[i]);
            userLabelMapper.insert(userLabel);
        }
        userQuestionMapper.deleteByUserId(userid);
        for(int i=0;i<introductionVO.getQuestionList().length;i++){
            UserQuestion userQuestion=new UserQuestion();
            userQuestion.setAnswer(introductionVO.getQuestionList()[i].getAnswer());
            userQuestion.setQuestionid(introductionVO.getQuestionList()[i].getQuestionid());
            userQuestion.setUserid(user.getId());
            userQuestionMapper.insert(userQuestion);
        }
        return new ReturnMessage(true,"ok");
    }
}
