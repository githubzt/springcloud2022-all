package com.zhangtao.springcloud.framework.netty;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月29日 17:26
 */
public class NettyServer {

    public void start(String hostName,int port){
        try {
            final ServerBootstrap bootstrap = new ServerBootstrap();
            NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
            /*bootstrap.group(eventExecutors)
                    .channel(NioServerSocketChannel.class)
                    .childHandler((ChannelInitializer) (socketChannel) -> {
                        ChannelPipeline pipeline = socketChannel().; //该处有问题 需要重写
                        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers
                                .weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        pipeline.addLast("encoder", new ObjectEncoder());
                        pipeline.addLast("handler", new NettyClientHandler());
                    });
            bootstrap.bind(hostName, port).sync();*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}