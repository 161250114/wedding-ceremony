package com.wedding.weddingconsulthappiness.service;


import com.wedding.weddingconsulthappiness.vo.HappinessPhotoVO;

import java.util.List;

public interface HappinessPhotoService {

    public int add(HappinessPhotoVO[] list);

    public List<List<String>> getPhotoList(int[] Ids);
}
