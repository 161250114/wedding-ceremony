package com.wedding.usermanage.service.impl;

import com.wedding.mapper.*;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.*;
import com.wedding.usermanage.service.LRService;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service("lrService")
public class LRServiceImpl implements LRService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private Date_standardMapper date_standardMapper;
    @Autowired
    private User_limitMapper user_limitMapper;
    @Autowired
    private UserLabelMapper userLabelMapper;
    @Autowired
    private UserQuestionMapper userQuestionMapper;

    @Override
    public LoginVO login(LoginVO loginVO) {
        User user=userMapper.selectByPhone(loginVO.getUname_phone());
        if(user!=null&&user.getPassword().equals(loginVO.getPassword())){
            return new LoginVO(user.getId(),loginVO.getUname_phone(),"");
        }else{
            user=userMapper.selectByUsername(loginVO.getUname_phone());
            if(user!=null&&user.getPassword().equals(loginVO.getPassword())){
                return new LoginVO(user.getId(),loginVO.getUname_phone(),"");
            }
        }
        return new LoginVO(0,"","");
    }

    @Override
    public ReturnMessage register(RegisterVO registerVO) {
        //添加相册
        Album album=new Album();
        album.setMaxNumber(5);
        album.setCurrentNumber(0);
        albumMapper.insert(album);
        //添加用户信息
        User user=new User();
        user.setAddress(registerVO.getAddress());
        user.setAlbumid(album.getId());
        user.setBalance(0);
        DateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirthday(format1.parse(registerVO.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEducation(registerVO.getEducation());
        user.setFullname(registerVO.getFullName());
        user.setHeight(registerVO.getHeight());
        user.setIntroduction(registerVO.getIntroduction());
        user.setMarrige((byte)registerVO.getMarriage());
        user.setPassword(registerVO.getPassword());
        user.setPhone(registerVO.getPhone());
        user.setProfession(registerVO.getProfession());
        user.setSalary(registerVO.getSalary());
        user.setSex((byte)registerVO.getSex());
        user.setTrueness(60);
        user.setUsername(registerVO.getUsername());
        user.setUsertype((byte)0);
        user.setDateStatus((byte)0);
        userMapper.insert(user);

        User_limit user_limit=new User_limit();
        user_limit.setLimitBrowse((byte)0);
        user_limit.setLimitDapply((byte)0);
        user_limit.setLimitFapply((byte)0);
        user_limit.setLimitMessage((byte)0);
        user_limit.setUserid(user.getId());
        user_limitMapper.insert(user_limit);
        //添加用户择偶标准
        Date_standard date_standard=new Date_standard();
        date_standard.setAddress(registerVO.getAddress());
        int age=getAge(registerVO.getBirthday());
        if(age<21){
            date_standard.setAgemax(age+3);
            date_standard.setAgemin(18);
        }else if(age>96){
            date_standard.setAgemax(99);
            date_standard.setAgemin(age-3);
        }else{
            date_standard.setAgemin(age-3);
            date_standard.setAgemax(age+3);
        }
        date_standard.setHeightmin(0);
        date_standard.setHeightmax(999);
        date_standard.setEducation("不限");
        date_standard.setMarrige((byte)4);
        date_standard.setSalary("不限");
        date_standard.setUserid(user.getId());
        date_standardMapper.insert(date_standard);
        //添加用户标签信息
        for(int i=0;i<registerVO.getTagList().length;i++){
            UserLabel userLabel=new UserLabel();
            userLabel.setUserId(user.getId());
            userLabel.setLabel(registerVO.getTagList()[i]);
            userLabelMapper.insert(userLabel);
        }
        //添加用户问题信息
        for(int i=0;i<registerVO.getQuestionList().length;i++){
            UserQuestion userQuestion=new UserQuestion();
            userQuestion.setAnswer(registerVO.getQuestionList()[i].getAnswer());
            userQuestion.setQuestionid(registerVO.getQuestionList()[i].getQuestionid());
            userQuestion.setUserid(user.getId());
            userQuestionMapper.insert(userQuestion);
        }
        return new ReturnMessage(true,"OK");
    }

    @Override
    public ReturnMessage validate(String phone) {
        return null;
    }

    public static  int getAge(String birthDay) {
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期

        int yearBirth = Integer.parseInt(birthDay.substring(0,4));
        int monthBirth = Integer.parseInt(birthDay.substring(5,7));
        int dayOfMonthBirth = Integer.parseInt(birthDay.substring(8));
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            }else{
                age--;//当前月份在生日之前，年龄减一
            } } return age; }
}
