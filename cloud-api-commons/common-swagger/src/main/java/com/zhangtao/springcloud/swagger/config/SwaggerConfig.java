package com.zhangtao.springcloud.swagger.config;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * 1. @ClassDescription: swagger配置类
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月04日 10:53
 */
@Configuration
@Slf4j
@EnableOpenApi //开启swagger
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
// 当配置中存在swagger.enabled生效，matchIfMissing = true默认生效
public class SwaggerConfig {

    @Resource
    private SwaggerProperties swaggerProperties;


    @Bean   // 相当于Spring 配置中的<bean>
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                swaggerProperties.getContact().getName(),
                swaggerProperties.getContact().getUrl(),
                swaggerProperties.getContact().getEmail()
        );

        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl("http://www.baidu.com")
                .contact(contact)
                .version(swaggerProperties.getVersion())
                .build();
    }

}