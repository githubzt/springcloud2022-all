package com.zhantao.springcloud.service;

import org.apache.dubbo.common.stream.StreamObserver;

/**
 * 1. @ClassDescription:   1、定义了全新的rpc(远程方法调用)通信协议-Tripe 基于HTTP/2上构建的RPC协议，完全兼容gRPC
 *                         2、多语言友好，使用Protobuf来实现   go语言调用dubbo
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 19:56
 */
public interface UserService {
    /**unary 就是正常调用方式**/
    public String getUser();

    /**SERVER_STREAM 服务端流式调用 服务实现类对应的方法**/
    default void sayHelloServerStream(String name, StreamObserver<String> response){

    }
    /**client_STREAM /BI_STREAM(双端流) 客户端流式调用 服务实现类对应的方法**/
    default StreamObserver<String> sayHelloStream(StreamObserver<String> response){
        return response;
    }
}
