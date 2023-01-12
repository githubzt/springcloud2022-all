package com.zhantao.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription: 服务流量管理
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月08日 11:21
 */
@RestController
@Slf4j
public class CentinelFlowLimitController {

    @GetMapping(value = "/testA")
    public String TestA(){
        try{
            TimeUnit.MILLISECONDS.sleep(800); //0.8秒
        }catch (Exception e){
            e.printStackTrace();
        }
        return "-------A";
    }

    @GetMapping(value = "/testB")
    public String TestB(){
        log.info(Thread.currentThread().getName()+"\t"+"*****B");
        return "-------B";
    }

    @GetMapping(value = "/testD")
    public String TestD(){
/*        try{
           TimeUnit.SECONDS.sleep(1); //暂停1秒种
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("测试testD 测试RT,慢调用");*/
        log.info("测试testD 测试异常比例.");
        int age = 10/0;
        return "--------D";
    }

    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    @GetMapping(value = "/testhotkey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                              @RequestParam(value = "p2",required = false)String p2){
        log.info("------- testHotKey,成功！");
        return "------- testHotKey,成功！";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        log.info("------- deal_testHotKey,失败！");
        return "-------deal_testHotKey,失败！";
    }


}