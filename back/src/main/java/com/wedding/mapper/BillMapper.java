package com.wedding.mapper;

import com.wedding.model.po.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bill record);

    Bill selectByPrimaryKey(Integer id);

    List<Bill> selectAll();

    int updateByPrimaryKey(Bill record);
}