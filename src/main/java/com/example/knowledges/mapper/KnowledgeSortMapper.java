package com.example.knowledges.mapper;

import com.example.knowledges.model.KnowledgeSort;

public interface KnowledgeSortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KnowledgeSort record);

    int insertSelective(KnowledgeSort record);

    KnowledgeSort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KnowledgeSort record);

    int updateByPrimaryKey(KnowledgeSort record);
}