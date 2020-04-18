package com.wedding.mapper;

import com.wedding.model.po.Bill;
import java.util.List;

public interface BillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bill record);

    Bill selectByPrimaryKey(Integer id);

    List<Bill> selectAll();

    int updateByPrimaryKey(Bill record);
}