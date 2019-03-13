package com.example.knowledges.servise.impl;

import com.example.knowledges.bean.KnowledgeBean;
import com.example.knowledges.mapper.KnowledgeMapper;
import com.example.knowledges.model.Knowledge;
import com.example.knowledges.servise.KnowledgeService;
import com.example.knowledges.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: KnowledgeServiceImpl
 * @Description: 知识库的相关操作
 * @author: LZA
 * @date: 2019-03-07
 */
@Service
@Slf4j
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Value("${File.filePath}")
    private String filePath;

    @Autowired
    private FileServiceImpl fileService;

    @Override
    public PageInfo<Knowledge> queryKnowledgeBySort(int pageNum, int pageSize,Integer sort) {
        PageHelper.startPage(pageNum,pageSize);
        List<Knowledge> knowledges =knowledgeMapper.queryKnowledgeBySort(sort);
        PageInfo<Knowledge> knowledgeAll=new PageInfo<>(knowledges);
        return knowledgeAll;
    }

    @Override
    public PageInfo<KnowledgeBean> queryKnowledgeAll(int pageNum, int pageSize, KnowledgeBean knowledgeBean) {
        PageHelper.startPage(pageNum,pageSize);
        List<KnowledgeBean> knowledges =knowledgeMapper.queryKnowledgeAll(knowledgeBean);
        PageInfo<KnowledgeBean> knowledgeBeanAll=new PageInfo<>(knowledges);
        return knowledgeBeanAll;
    }

    @Override
    public void addKnowledge(Knowledge knowledge,HttpServletRequest request) {
        Map map =fileService.handleFileUpload(request);
        String docPath= (String) map.get("docPath");
        String imagePath= (String) map.get("jpgPath");
        String videoPath= (String) map.get("mp4Path");
        String id=RandomUtil.getRandomString(8)+"-"+RandomUtil.getRandomString(4)+"-"+RandomUtil.getRandomString(4)
                +"-"+RandomUtil.getRandomString(4)+"-"+RandomUtil.getRandomString(12);
        knowledge.setId(id);
        knowledge.setImagePath(imagePath);
        knowledge.setDocPath(docPath);
        knowledge.setVideoPath(videoPath);
        knowledge.setBuildTime(new Date());
//        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
//        String time = dFormat.format(buildTime);
        log.info("添加的时间》》》"+knowledge.getBuildTime());
        log.info("当前时间》》》》"+System.currentTimeMillis());
        knowledgeMapper.insertSelective(knowledge);
    }

    @Override
    public String downloadFile(HttpServletRequest request, HttpServletResponse response ,String path )throws UnsupportedEncodingException {
//        String[] imgUrls=path.split(",");
//        for (String imgUrl:imgUrls) {
            int index = filePath.lastIndexOf("//");
            String fileName=path.substring(index+2);
            String result=fileService.downloadFile(request,response,fileName);
            log.info(fileName+">>>>>>"+result);
//        }
        return "success";
    }

    @Override
    public String downloadFileMore(String Id) {
        //需要压缩的文件--包括文件地址和文件名
        String imgPath=knowledgeMapper.queryImgPathById(Id);
        return fileService.downloadFileMore(imgPath);
    }

    @Override
    public void deleteBatchByIds(List<String> Ids) {
        knowledgeMapper.deleteBatchByIds(Ids);
    }

    @Override
    public Knowledge queryKnowledgeById(String Id) {
        return knowledgeMapper.queryKnowledgeById(Id);
    }

    @Override
    public void updateKnowledge(Knowledge knowledge,HttpServletRequest request) {
        Map map =fileService.handleFileUpload(request);
        String docPath= (String) map.get("docPath");
        String imagePath= (String) map.get("jpgPath");
        String videoPath= (String) map.get("mp4Path");
        knowledge.setImagePath(imagePath);
        knowledge.setDocPath(docPath);
        knowledge.setVideoPath(videoPath);
        knowledge.setBuildTime(new Date());
        log.info("修改的时间》》》"+knowledge.getBuildTime());
        log.info("当前时间》》》》"+System.currentTimeMillis());
        knowledgeMapper.updateKnowledge(knowledge);
    }

}
