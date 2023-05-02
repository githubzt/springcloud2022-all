package com.zhangtao.springcloud.framework.netty;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import com.zhangtao.springcloud.framework.Invocation;

import java.util.concurrent.Callable;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月29日 17:26
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private Invocation invocation;
    private String result;

    @Override
    public void channelActive(ChannelHandlerContext cht) throws Exception{

    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext cht,Object msg) throws Exception{
        result = msg.toString();
        notify();
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}