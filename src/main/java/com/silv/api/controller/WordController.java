package com.silv.api.controller;

import com.silv.api.model.Result;
import com.silv.api.service.DocTemplateService;
import com.silv.api.util.ResultUtil;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("word")
public class WordController {

    @Autowired
    private DocTemplateService docTemplateService;

    @PostMapping
    public Result readWord(@RequestParam String path) {
        String name = path.substring(path.lastIndexOf("\\") + 1);
        String text = "";
        try {
            if (path.endsWith("doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                text = ex.getText();
                return this.docTemplateService.saveWordTemplate(text, name);
            } else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                text = extractor.getText();
                return this.docTemplateService.saveWordTemplate(text, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(0, "文件读取错误");
        }
        return ResultUtil.error(0, "不是word文件");
    }

}
