package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.util.IdGeneratorSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月11日 10:37
 */
@Service
@Slf4j
public class IdWorkerService {

    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;

    public String getIdBySnowFlake(){
        //新建5个线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for(int i=1;i<=20 ;i++){
            threadPool.submit(()->{
                System.out.println(idGeneratorSnowflake.sonwflakeId());
            });
        }
        threadPool.shutdown();
        return "hello snowflake";
    }

}