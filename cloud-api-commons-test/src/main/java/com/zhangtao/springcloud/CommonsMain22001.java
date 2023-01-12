package com.zhangtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月04日 12:32
 */
@EnableOpenApi
@SpringBootApplication
public class CommonsMain22001 {

    public static void main(String[] args) {
        SpringApplication.run(CommonsMain22001.class,args);
    }
}