package com.wedding.mapper;

import com.wedding.model.po.Search;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Search record);

    Search selectByPrimaryKey(Integer id);

    List<Search> selectAll();

    int updateByPrimaryKey(Search record);
}