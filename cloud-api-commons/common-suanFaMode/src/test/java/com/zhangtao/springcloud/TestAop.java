package com.zhangtao.springcloud;

import com.zhangtao.springcloud.aop.CalcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月06日 10:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAop {

    @Resource
    private CalcService calcService;

    @Test
    public void test1(){
        System.out.println("spring版本: " + SpringVersion.getVersion() + " \t springboot版本: " + SpringBootVersion.getVersion());
        calcService.div(10,0);
    }

}