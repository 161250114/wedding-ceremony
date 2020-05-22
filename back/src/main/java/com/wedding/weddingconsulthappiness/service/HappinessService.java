package com.wedding.weddingconsulthappiness.service;


import com.wedding.model.po.Happiness;
import com.wedding.model.po.User;

import java.util.List;

public interface HappinessService {

    public int add(Happiness h);

    public List<Happiness> get(int[] Ids);

    public List<Happiness> getAll();

    public int getId();

    public int del(int id);

}
