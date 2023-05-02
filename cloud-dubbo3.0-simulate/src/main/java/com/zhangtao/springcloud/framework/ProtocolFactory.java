package com.zhangtao.springcloud.framework;

import com.zhangtao.springcloud.framework.http.HttpProtocol;
import com.zhangtao.springcloud.framework.netty.DubboProtocol;

/**
 * 1. @ClassDescription:  读取配置中的协议   dubbo 用的 SPI 来读取配置协议
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月31日 19:57
 */
public class ProtocolFactory {

    public static Protocol getProtocol(){
        String name = System.getProperty("protocolName");
        if(name == null || name.equals("")){
            name = "http";
        }
        switch (name){
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return new DubboProtocol();
            default:
                break;
        }
        return new HttpProtocol();
    }
}