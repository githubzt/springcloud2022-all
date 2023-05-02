package com.zhangtao.springcloud.framework.netty;


import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import org.apache.catalina.startup.Bootstrap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1. @ClassDescription:  netty协议
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月29日 17:25
 */
public class NettyClient {

    public NettyClientHandler client = null;

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(String hostName,Integer port){
        client = new NettyClientHandler();

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

    }
}