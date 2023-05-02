package com.zhangtao.springcloud.consumer;

import com.zhangtao.springcloud.framework.Invocation;
import com.zhangtao.springcloud.framework.ProxyFactory;
import com.zhangtao.springcloud.framework.http.HttpClient;
import com.zhangtao.springcloud.provider.api.HelloService;

import java.io.IOException;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月23日 16:28
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        String result = "";
        //方式二   代理方式
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        result = helloService.sayHello("zhangtao1213");
        System.out.println("方式二: " + result);

        //方式一   最原始方式
        HttpClient httpClient = new HttpClient();
        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Class[]{String.class}, new Object[]{"zhangtao"});
        result = httpClient.send("localhost", 8080, invocation);
        System.out.println("方式一:" + result);

    }

}