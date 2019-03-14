package com.example.knowledges.controller;

import com.example.knowledges.bean.KnowledgeBean;
import com.example.knowledges.model.Knowledge;
import com.example.knowledges.servise.impl.FileServiceImpl;
import com.example.knowledges.servise.impl.KnowledgeServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName: KnowledgeControlle
 * @Description: 知识库的相关操作
 * @author: LZA
 * @date: 2019-03-08
 */
@RestController
@RequestMapping("knowledge")
public class KnowledgeControlle {

    @Autowired
    private KnowledgeServiceImpl knowledgeService;

    @Autowired
    private FileServiceImpl fileService;

    /**测试问题分类加分页查询*/
    @GetMapping("queryKnowledgeBySort")
    public PageInfo<KnowledgeBean> queryKnowledgeBySort(int pageNum, int pageSize,@RequestParam("sortList") List<Integer> sortList){
        return knowledgeService.queryKnowledgeBySort(pageNum,pageSize,sortList);
    }

    /**多条件查询（问题模糊查询、关键词模糊查询、商品名称模糊查询、
     *  商家编号模糊查询、部门查询、种类Sort查询、分页查询）
     */
    @GetMapping("queryKnowledgeAll")
    public PageInfo<KnowledgeBean> queryKnowledgeAll(int pageNum, int pageSize,KnowledgeBean knowledgeBean){
        return knowledgeService.queryKnowledgeAll(pageNum,pageSize,knowledgeBean);
    }

    /**
     * 添加知识库资料（上传文件）
     * 部门（data_org）、商品名称、商家编号、问题分类(sort)、生产厂家、答案、问题、上传的文件（视频、图片、文档）
     */
    @PostMapping("addKnowledge")
    public void addKnowledge(Knowledge knowledge,HttpServletRequest request)  {
        knowledgeService.addKnowledge(knowledge,request);
    }

    /**
     * 下载知识库图片
     */
    @GetMapping("downloadFile")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,String path )throws UnsupportedEncodingException {
        return knowledgeService.downloadFile(request,response,path);
    }

    /**
     * 下载多个知识库图片（压缩包）
     */
    @GetMapping("downloadFileMore")
    public String downloadFileMore(String Id){
        return knowledgeService.downloadFileMore(Id);
    }

    /**
     * 批量删除
     */
    @PostMapping("deleteBatchByIds")
    public void deleteBatchByIds(@RequestBody List<String> Ids){
        knowledgeService.deleteBatchByIds(Ids);
    }

    /**
     * 去修改，根据id去查询数据信息
     */
    @GetMapping("queryKnowledgeById")
    public Knowledge queryKnowledgeById( String Id){
        return knowledgeService.queryKnowledgeById(Id);
    }

    /**
     * 知识库数据修改操作
     */
    @RequestMapping(value="updateKnowledge",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    public void updateKnowledge(Knowledge knowledge,HttpServletRequest request){
        knowledgeService.updateKnowledge(knowledge,request);
    }

}
