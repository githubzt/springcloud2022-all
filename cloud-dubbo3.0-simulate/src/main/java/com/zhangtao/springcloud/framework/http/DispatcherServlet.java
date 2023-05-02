package com.zhangtao.springcloud.framework.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. @ClassDescription:  重写Dispatchserlet
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月23日 17:08
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //读取配置 具体使用那个handler
        new HttpServerHandler().handler(req,resp);

    }
}