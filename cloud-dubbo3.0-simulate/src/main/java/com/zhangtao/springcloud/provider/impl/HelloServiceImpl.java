package com.zhangtao.springcloud.provider.impl;

import com.zhangtao.springcloud.provider.api.HelloService;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月23日 16:34
 */

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String userName) {
        return "Hello: " + userName;
    }

}