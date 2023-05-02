package com.zhangtao.springcloud.framework;

import java.util.List;
import java.util.Random;

/**
 * 1. @ClassDescription:  负载均衡
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月24日 20:31
 */
public class LoadBalance {

    public static URL random(List<URL> list){
        Random random = new Random();
        int i = random.nextInt(list.size());
        return list.get(i);
    }
}