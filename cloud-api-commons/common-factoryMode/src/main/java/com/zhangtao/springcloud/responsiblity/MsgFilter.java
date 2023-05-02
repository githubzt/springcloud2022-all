package com.zhangtao.springcloud.responsiblity;

/**
 * 1. @ClassDescription:  定义处理敏感词的类，实现处理消息的接口：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月16日 22:48
 */
public class MsgFilter implements FatherFilter{
    /**处理消息的类,过滤消息**/
    @Override
    public String doFilrter(String msg) {
        String s = msg.replace("*", "");
        System.out.println("MsgFilter repalce msg: " + s);
        return s;
    }
}