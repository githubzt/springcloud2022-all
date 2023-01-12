package com.zhangtao.springcloud.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 1. @ClassDescription:  雪花算法加工util
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月11日 10:47
 */
@Component
@Slf4j
public class IdGeneratorSnowflake {

    private long workerId = 0; //第几号机房
    private long datacenterId = 1; //第几号机器
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);

    @PostConstruct //构造后开始执行，加载初始化工作
    public void init(){

        try{
            //获取本机的ip地址编码
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workid: {}",workerId);
        }catch (Exception e){
            e.printStackTrace();
            log.warn("当前机器的workid获取失败！",e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

    }

    public synchronized  long sonwflakeId(){
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(Long workerId , long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        System.out.println(new IdGeneratorSnowflake().sonwflakeId());
    }


}