package com.wedding.mapper;

import com.wedding.model.po.LabelHeat;
import java.util.List;

public interface LabelHeatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LabelHeat record);

    LabelHeat selectByPrimaryKey(Integer id);

    List<LabelHeat> selectAll();

    int updateByPrimaryKey(LabelHeat record);

    List<LabelHeat> selectHottest();

    int updateByLabel(String label);
}