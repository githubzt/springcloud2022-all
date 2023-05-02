package com.zhantao.springcloud.springcloud.service;

import com.alibaba.fastjson.JSON;
import com.zhantao.springcloud.springcloud.pojo.Content;
import com.zhantao.springcloud.springcloud.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月14日 15:10
 */
@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //1、解析数据放入es索引中
    public Boolean parseContent(String keywords) throws Exception{
        List<Content> parseJD = new HtmlParseUtil().parseJD("java");
        //查询数据批量放入es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        for (int i = 0; i < parseJD.size(); i++) {
            bulkRequest.add(new IndexRequest("jd_goods").source(JSON.toJSONString(parseJD.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return bulkResponse.hasFailures();
    }

    //2、获取这些数据实现搜索功能
    public List<Map<String,Object>> searchPage(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo<=1) {
            pageNo = 1;
        }
        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        //构建查询器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //精准匹配termQuery     QueryBuilders 查询条件工具类
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(queryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        //高亮  这个是单独拉出来的一个集合 需要替换原来结果集
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false); //多个高亮显示, false只显示一个
        highlightBuilder.preTags("<span style='color:red'>"); highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter();

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> goodsList = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            //解析高亮的字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField tile = highlightFields.get("title");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap(); //获取所有数据
            //解析高亮的字段，将原来的字段换为我们高亮的字段即可
            if(tile!=null){
                Text[] fragments = tile.fragments();
                String n_tile = "";
                for (Text text : fragments) {
                    n_tile += text;
                }
                sourceAsMap.put("title",n_tile); //高亮字段替换掉原来的内容即可
            }
            goodsList.add(sourceAsMap);
        }
        return goodsList;
    }
}