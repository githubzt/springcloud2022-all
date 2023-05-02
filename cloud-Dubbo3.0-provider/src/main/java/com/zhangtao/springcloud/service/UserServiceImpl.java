package com.zhangtao.springcloud.service;

import com.zhantao.springcloud.service.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月16日 17:14
 */
@DubboService
public class UserServiceImpl implements UserService {

    @Override
    public String getUser(){
        return "zhangtao";
    }
    /** Server_Stream **/
    @Override
    public void sayHelloServerStream(String name, StreamObserver<String> response) {
        //处理name
        response.onNext("hello: " + name);
        //处理name  可以处理n次
        response.onNext("结束：" + name);
        /** 处理完后调用这个方法告诉客户端的消费者本次请求结束了 **/
        response.onCompleted();

   /*   // 第一次处理
        response.onNext("姓名检查中");
        System.out.println("姓名为" + name);
        // 可以处理多次
        if(null == name || "".equals(name.trim())){
            try {
                response.onError(new RuntimeException("用户不存在"));
                System.out.println("测试onError下面代码是否会继续执行");
                response.onError(new RuntimeException("用户真的不存在"));
                response.onNext("我喜欢王某");
            }catch (Exception e){
                System.out.println("但是我抛出了异常");
            }
        }else{
            response.onNext("欢迎您登录系统:" + name);
        }
        // 结束
        response.onCompleted();*/

    }
    /** Client_Stream **/
    @Override
    public StreamObserver<String> sayHelloStream(StreamObserver<String> response) {

        return new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("服务端收到的数据: " + data);

                //处理data
                /**客户端不断的给服务端发送数据，服务端也不断的响应客户端 双端流**/
                response.onNext("响应结果: " + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("发送完成");
            }
        };
    }
}