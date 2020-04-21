package com.wedding.usermanage.service.impl;

import com.wedding.mapper.AlbumMapper;
import com.wedding.mapper.Date_standardMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.Album;
import com.wedding.model.po.Date_standard;
import com.wedding.model.po.User;
import com.wedding.usermanage.service.LRService;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service("lrService")
public class LRServiceImpl implements LRService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private Date_standardMapper date_standardMapper;

    @Override
    public ReturnMessage login(LoginVO loginVO) {
        User user=userMapper.selectByPhone(loginVO.getUname_phone());
        if(user.getPassword().equals(loginVO.getPassword())){
            return new ReturnMessage(true,"ok");
        }else{
            user=userMapper.selectByUsername(loginVO.getUname_phone());
            if(user.getPassword().equals(loginVO.getPassword())){
                return new ReturnMessage(true,"ok");
            }
        }
        return new ReturnMessage(false,"密码不正确");
    }

    @Override
    public ReturnMessage register(RegisterVO registerVO) {
        Album album=new Album();
        album.setMaxNumber(5);
        album.setCurrentNumber(0);
        albumMapper.insert(album);

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
        user.setLimitBrowse((byte)0);
        user.setLimitMessage((byte)0);
        user.setMarrige((byte)registerVO.getMarriage());
        user.setPassword(registerVO.getPassword());
        user.setPhone(registerVO.getPhone());
        user.setProfession(registerVO.getProfession());
        user.setSalary(registerVO.getSalary());
        user.setSex((byte)registerVO.getSex());
        user.setTrueness(60);
        user.setUsername(registerVO.getUsername());
        user.setUsertype((byte)0);
        userMapper.insert(user);
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
