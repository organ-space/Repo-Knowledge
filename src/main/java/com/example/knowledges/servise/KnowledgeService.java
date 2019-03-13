package com.example.knowledges.servise;

import com.example.knowledges.bean.KnowledgeBean;
import com.example.knowledges.model.Knowledge;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;


public interface KnowledgeService {

    /** 测试，使用注解做条件筛选*/
    PageInfo<Knowledge> queryKnowledgeBySort(int pageNum, int pageSize, Integer sort);

    /** 条件筛选知识库*/
    PageInfo<KnowledgeBean> queryKnowledgeAll(int pageNum, int pageSize, KnowledgeBean knowledgeBean);

    /** 添加知识库资料*/
    void addKnowledge(Knowledge knowledge, HttpServletRequest request);

    /** 根据id编号查询图片路径并根据路径下载图片*/
    String downloadFile(HttpServletRequest request, HttpServletResponse response, String path) throws UnsupportedEncodingException;

    /** 实现多文件打包下载*/
    String downloadFileMore(String Id);

    /** 批量删除*/
    void deleteBatchByIds(List<String> Ids);

    /** 根据Id 查询知识库数据信息*/
    Knowledge queryKnowledgeById(String Id);

    /** 根据Id修改知识库数据*/
    void updateKnowledge(Knowledge knowledge, HttpServletRequest request);
}
