package com.zhangtao.springcloud.framework;

import com.zhangtao.springcloud.framework.http.HttpClient;
import com.zhangtao.springcloud.framework.register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月24日 19:25
 */
public class ProxyFactory<T> {

    public static <T> T getProxy(final Class interfaceClass){
        //读取用户配置 下面用jdk    RPC框架的代理工厂
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String result = "";
                //联调使用 mock 虚拟机配置:-Dmock=return:123  没开发完成前 返回一个结果
                String mock = System.getProperty("mock");
                if(mock != null && mock.startsWith("return:")){
                    String aReturn = mock.replace("return", "");
                    return aReturn;
                }

                int count = 0;
                while (count <= 3){
                    try {
                        //方式一
                        HttpClient httpClient = new HttpClient();
                        //方式二
                        Protocol protocol = ProtocolFactory.getProtocol();
                        //interfaceClass.getName() 服务
                        Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                        //注册中心  map<接口名 ,lsit<url> >
                        List<URL> urls = RemoteMapRegister.get(interfaceClass.getName());
                        //负载均衡   RPC框架
                        URL url = LoadBalance.random(urls);
                        //RPC协议

                        //方式一
                        result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                        //方式二
                        result = protocol.send(url, invocation);
                        return result;
                    }catch (RPCException e){
                        //容错功能实现   读取用户配置 class + 方法名
                        /**重试  while 调用次数统计 **/
                        if(count < 3){
                            count ++;
                            continue;
                        }
                        return "报错了!";
                    }
                }
                return result;
            }
        });
        return (T) proxyInstance;
    }
}