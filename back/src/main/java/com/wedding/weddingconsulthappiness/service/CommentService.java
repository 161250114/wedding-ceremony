package com.wedding.weddingconsulthappiness.service;



import com.wedding.model.po.Comment;

import java.util.List;

public interface CommentService {

    public int add(Comment c);

    public List<List<Comment>> getCommentList( int[] ids);
}
