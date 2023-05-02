package com.zhangtao.springcloud.framework.netty;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import com.zhangtao.springcloud.framework.Invocation;
import com.zhangtao.springcloud.framework.register.LocalRegister;

import java.lang.reflect.Method;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月29日 17:26
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext cht,Object obj) throws Exception{
        Invocation invocation = (Invocation) obj;

        Class serviceImpl = LocalRegister.get(invocation.getInterfaceName());

        Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParamType());
        Object result = method.invoke(serviceImpl.newInstance(), invocation.getParams());

        System.out.println("Netty------------" + result.toString());
        cht.writeAndFlush("Netty:"+result);

    }
}