package com.zhangtao.springcloud.framework;

import java.io.IOException;

/**
 * 1. @ClassDescription:  协议接口
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月29日 17:29
 */
public interface Protocol {

    void start(URL url);

    String send(URL url, Invocation invocation) throws IOException;
}
