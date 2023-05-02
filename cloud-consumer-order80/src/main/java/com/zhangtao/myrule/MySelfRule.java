package com.zhangtao.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. @ClassDescription:  自己实现负载均衡
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月17日 11:14
 */
@Configuration
public class MySelfRule {

    @Bean //随机负载
    public IRule iRule(){
        return new RandomRule();
    }

}