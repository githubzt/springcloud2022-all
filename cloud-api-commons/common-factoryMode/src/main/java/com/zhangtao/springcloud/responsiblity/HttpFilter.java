package com.zhangtao.springcloud.responsiblity;

/**
 * 1. @ClassDescription: 定义过滤特殊字符的类，实现处理消息的接口：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月16日 22:43
 */
public class HttpFilter implements FatherFilter{
    /**处理http消息的类 过滤http消息**/
    @Override
    public String doFilrter(String msg) {
        String s = msg.replace("-", "");
        System.out.println("HttpFilter replace msg: " + s);
        return s;
    }
}