package com.zhantao.springcloud.springcloud.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. @ClassDescription: 类似之前的xml文件
 *  学习方式： 1、找对象  2、待用  3、springboot 就先分析源码
 *        一般源码的自动配置文件目录： org.springframework.boot.autoconfigure.xxxx
 *          格式： xxxautoConfiguration  xxxProperties
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月13日 18:54
 */
@Configuration
public class ElasticSearchClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
        return restHighLevelClient;
    }

}