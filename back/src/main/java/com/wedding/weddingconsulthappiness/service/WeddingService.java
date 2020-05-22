package com.wedding.weddingconsulthappiness.service;


import com.wedding.model.po.Wedding;
import com.wedding.weddingconsulthappiness.vo.WeddingVO;

import java.util.List;

public interface WeddingService {

    public int addw(Wedding w);
    public List<WeddingVO> getw(Integer id);
    public List<Wedding> getAll();
    public int update(WeddingVO w);


}
