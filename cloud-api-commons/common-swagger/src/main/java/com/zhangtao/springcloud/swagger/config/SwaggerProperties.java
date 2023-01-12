package com.zhangtao.springcloud.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 1. @ClassDescription: swagger文件配置类
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月04日 10:56
 */
@Data
@Configuration
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 是否开启swagger
     */
    private Boolean enabled;

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "/**";

    /**
     * 标题
     **/
    private String title = "";

    /**
     * 描述
     **/
    private String description = "";

    /**
     * 版本
     **/
    private String version = "";

    /**
     * host信息
     **/
    private String host = "";

    /**
     * 联系人信息
     */
    private Contact contact = new Contact();


    @Data
    public static class Contact {
        /**
         * 联系人
         **/
        private String name;
        /**
         * 联系人url
         **/
        private String url;
        /**
         * 联系人email
         **/
        private String email;
    }

}