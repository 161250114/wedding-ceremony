package com.wedding.mapper;

import com.wedding.model.po.Friend_apply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Friend_applyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend_apply record);

    Friend_apply selectByPrimaryKey(Integer id);

    List<Friend_apply> selectAll();

    int updateByPrimaryKey(Friend_apply record);

    List<Friend_apply> selectByUserid1(int userid1);

    List<Friend_apply> selectByUserid2(int userid2);

}
