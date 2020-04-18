package com.wedding.usermanage.service.impl;

import com.wedding.mapper.CommentMapper;
import com.wedding.model.po.Comment;
import com.wedding.usermanage.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value="CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper cm;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cm.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Comment record) {
        return cm.insert(record);
    }

    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return cm.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(Comment record) {
        return cm.updateByPrimaryKey(record);
    }

    @Override
    public List<Comment> selectAll() {
        return cm.selectAll();
    }
}
