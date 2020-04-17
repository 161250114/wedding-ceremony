package com.wedding.usermanage.service;


import com.wedding.model.po.Comment;

import java.util.List;

public interface CommentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll();
}
