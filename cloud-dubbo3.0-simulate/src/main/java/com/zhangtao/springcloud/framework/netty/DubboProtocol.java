package com.zhangtao.springcloud.framework.netty;

import com.zhangtao.springcloud.framework.Invocation;
import com.zhangtao.springcloud.framework.Protocol;
import com.zhangtao.springcloud.framework.URL;

import java.io.IOException;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月31日 19:18
 */
public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new NettyServer().start(url.getHostname(),url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) throws IOException {
        return null;
    }
}