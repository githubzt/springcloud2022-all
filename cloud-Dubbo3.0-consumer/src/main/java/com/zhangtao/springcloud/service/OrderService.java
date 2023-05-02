package com.zhangtao.springcloud.service;

import com.zhantao.springcloud.service.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月16日 17:18
 */
@Service
public class OrderService{

    @DubboReference
    private UserService userService;
    //unary 方式
    public String getOrder(){
        return userService.getUser();
    }

    /**  Server_Stream 流式调用**/
    public void getOrderByServerStream(){
        userService.sayHelloServerStream("zhangsan", new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("接收到的结果为：" + data);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("接收出错啦...");
            }

            @Override
            public void onCompleted() {
                System.out.println("服务执行完毕！");
            }
        });
    }
    /**  client_Stream  双端流  流式调用**/
    public String getOrderByClientStream(){
        StreamObserver<String> stringStreamObserver = userService.sayHelloStream(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("消费端接收到的信息：" + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("消费端接收结束");
            }
        });
        /**服务消费发送的数**/
        stringStreamObserver.onNext("1");
        stringStreamObserver.onNext("2");
        stringStreamObserver.onNext("3");
        stringStreamObserver.onCompleted();
        return "sucess";
    }
}