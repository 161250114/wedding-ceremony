package com.wedding.usermanage.service.impl;

import com.wedding.mapper.AlbumMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.Album;
import com.wedding.model.po.User;
import com.wedding.usermanage.service.LRService;
import com.wedding.usermanage.vo.LoginVO;
import com.wedding.usermanage.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service("lrService")
public class LRServiceImpl implements LRService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public ReturnMessage login(LoginVO loginVO) {

        return new ReturnMessage(true,"ok");
    }

    @Override
    public ReturnMessage register(RegisterVO registerVO) {
        Album album=new Album();
        album.setMaxNumber(5);
        album.setCurrentNumber(0);
        albumMapper.insert(album);
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
}
