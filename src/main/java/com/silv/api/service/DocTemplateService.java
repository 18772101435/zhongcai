package com.silv.api.service;

import com.silv.api.dao.DocTemplateDao;
import com.silv.api.model.BaseDocTemplate;
import com.silv.api.model.Result;
import com.silv.api.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DocTemplateService {

    @Autowired
    private DocTemplateDao docTemplateDao;

    public Result saveWordTemplate(String content, String name) {
        BaseDocTemplate baseDocTemplate = new BaseDocTemplate();
        baseDocTemplate.setNo("zc"+System.currentTimeMillis());
        baseDocTemplate.setName(name);
        baseDocTemplate.setStatus(true);
        baseDocTemplate.setCreateTime(new Date());
        baseDocTemplate.setUpdateTime(new Date());
        baseDocTemplate.setContent(content);
        this.docTemplateDao.save(baseDocTemplate);
        return ResultUtil.success(baseDocTemplate);
    }

}
