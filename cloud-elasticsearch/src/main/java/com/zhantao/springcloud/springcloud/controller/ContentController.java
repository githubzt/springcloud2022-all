package com.zhantao.springcloud.springcloud.controller;

import com.zhantao.springcloud.springcloud.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月14日 15:07
 */
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keywords}")
    public Boolean parse(@PathVariable("keywords") String keywords) throws Exception{
        return  contentService.parseContent(keywords);
    }
    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String,Object>> search(@PathVariable("keyword")String keyword, @PathVariable("keyword")int pageNo,
                                           @PathVariable("keyword")int pageSize) throws IOException {
        if(pageNo==0) {
            pageNo=1;
        }
        if (pageSize==0) {
            pageSize = 10;
        }
        List<Map<String, Object>> mapList = contentService.searchPage(keyword, pageNo, pageSize);
        return mapList;
    }
}