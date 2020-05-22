package com.wedding.weddingconsulthappiness.service;

import com.wedding.model.po.Happiness_likes;

import java.util.List;

public interface HappinessLikesService {

    public int addl(int id,int userId);
    public List<Happiness_likes> getAlll();

    public List<Boolean> getMyLikes(int[] Ids,int userId);

    public List<Integer> getLikes(int[] Ids);

}
