package com.zhangtao.springcloud.provider;

import com.zhangtao.springcloud.framework.ProtocolFactory;
import com.zhangtao.springcloud.framework.Protocol;
import com.zhangtao.springcloud.framework.URL;
import com.zhangtao.springcloud.framework.http.HttpServer;
import com.zhangtao.springcloud.framework.netty.NettyServer;
import com.zhangtao.springcloud.framework.register.LocalRegister;
import com.zhangtao.springcloud.framework.register.RemoteMapRegister;
import com.zhangtao.springcloud.provider.api.HelloService;
import com.zhangtao.springcloud.provider.impl.HelloServiceImpl;

import java.net.MalformedURLException;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月23日 16:30
 */
public class Provider {

    public static void main(String[] args) throws MalformedURLException {
        //启动  网络 接收请求
        //Tomcat Netty Jetty  SocketServer  配置自己想用的启动工具

        //本地注册 要提供的服务
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);
        //远程注册  接口名 应用IP + 端口
        URL url = new URL("localhost",8080);
        RemoteMapRegister.regist(HelloService.class.getName(),url);

        //读取配置 方式一： 启动Tomcat
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());
        //方式二
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(),url.getPort());
        //方式三  抽象接口 读取配置文件中配置的协议
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}