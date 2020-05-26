package com.wedding.usermanage.service.impl;

import com.wedding.mapper.*;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.*;
import com.wedding.usermanage.service.UserInfoService;
import com.wedding.usermanage.utils.Base64Converter;
import com.wedding.usermanage.utils.CosClient;
import com.wedding.usermanage.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    @Autowired
    private Album_photoMapper album_photoMapper;
    @Autowired
    private Date_recordMapper date_recordMapper;
    @Autowired
    private Date_applyMapper date_applyMapper;
    @Autowired
    private Friend_applyMapper friend_applyMapper;

    @Override
    public ReturnMessage getUserStatus(int userid) {
        User user=userMapper.selectByPrimaryKey(userid);
        UserStatusVO userStatusVO=new UserStatusVO();
        List<Album_photo> photos=album_photoMapper.selectByAlbumId(user.getAlbumid());
        for(int i=0;i<photos.size();i++){
            if(photos.get(i).getOrderNumber()==1){
                userStatusVO.setHeadPhotoUrl(CosClient.bucket_url+photos.get(i).getAddress());
                break;
            }
        }
        List<Date_record> date_records=date_recordMapper.selectByUserId1AndUserId2(userid);
        for(int i=0;i<date_records.size();i++){
            if(date_records.get(i).getEndDate()==null){
                userStatusVO.setDateFriendId(date_records.get(i).getUserid2());
                if(userid==date_records.get(i).getUserid2()){
                    userStatusVO.setDateFriendId(date_records.get(i).getUserid1());
                }
            }
        }
        userStatusVO.setBalance(user.getBalance());
        userStatusVO.setDateStatus(user.getDateStatus());
        userStatusVO.setUserid(userid);
        userStatusVO.setUsername(user.getUsername());
        userStatusVO.setUsertype(user.getUsertype());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        if(user.getVipEnddate()!=null){
            userStatusVO.setVipEnddate(format.format(user.getVipEnddate()));
        }else{
            userStatusVO.setVipEnddate("当前不是会员");
        }
        StringBuilder sb=new StringBuilder(user.getPhone());
        sb.replace(3,7,"****");
        userStatusVO.setPhone(sb.toString());

        int newDapplyNum=0;
        List<Date_apply> date_applies=date_applyMapper.selectByUserid2(userid);
        for(int i=0;i<date_applies.size();i++){
            if(date_applies.get(i).getResult().equals("等待")){
                newDapplyNum++;
            }
        }
        userStatusVO.setNewDapplyNum(newDapplyNum);

        int newFapplyNum=0;
        List<Friend_apply> friend_applies=friend_applyMapper.selectByUserid2(userid);
        for(int i=0;i<friend_applies.size();i++){
            if(friend_applies.get(i).getResult().equals("等待")){
                newFapplyNum++;
            }
        }
        userStatusVO.setNewFapplyNum(newFapplyNum);
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
        //调整标签信息
        for(int i=0;i<introductionVO.getTagList().length;i++){
            UserLabel userLabel=new UserLabel();
            userLabel.setUserId(user.getId());
            userLabel.setLabel(introductionVO.getTagList()[i]);
            userLabelMapper.insert(userLabel);
        }
        //调整兴趣爱好信息
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

    @Override
    public ReturnMessage changePassword(int userid, PasswordVO passwordVO) {
        User user=userMapper.selectByPrimaryKey(userid);
        //判断解密后密码与输入密码是否一致
        if(Base64Converter.decode(user.getPassword()).equals(passwordVO.getOldPassword())){
            //数据库存储新的加密密码
            user.setPassword(Base64Converter.encode(passwordVO.getNewPassword()));
            userMapper.updateByPrimaryKey(user);
            return new ReturnMessage(true,"修改成功");
        }else{
            return new ReturnMessage(false,"旧密码输入不正确！");
        }
    }

    @Override
    public ReturnMessage changePhone(int userid, String newPhone) {
        User user=userMapper.selectByPrimaryKey(userid);
        user.setPhone(newPhone);
        userMapper.updateByPrimaryKey(user);
        return new ReturnMessage(true,"修改成功!");
    }

    @Override
    public String getPhone(int userid) {
        User user=userMapper.selectByPrimaryKey(userid);
        return user.getPhone();
    }
}
