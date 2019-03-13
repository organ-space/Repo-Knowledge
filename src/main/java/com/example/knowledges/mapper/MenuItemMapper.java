package com.example.knowledges.mapper;

import com.example.knowledges.model.MenuItem;

public interface MenuItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(MenuItem record);

    int insertSelective(MenuItem record);

    MenuItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MenuItem record);

    int updateByPrimaryKey(MenuItem record);
}