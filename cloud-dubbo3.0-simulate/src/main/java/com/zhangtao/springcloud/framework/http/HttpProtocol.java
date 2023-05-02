package com.zhangtao.springcloud.framework.http;

import com.zhangtao.springcloud.framework.Invocation;
import com.zhangtao.springcloud.framework.Protocol;
import com.zhangtao.springcloud.framework.URL;

import java.io.IOException;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月31日 19:14
 */
public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new HttpServer().start(url.getHostname(),url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) throws IOException {
        return new HttpClient().send(url.getHostname(),url.getPort(),invocation);
    }
}