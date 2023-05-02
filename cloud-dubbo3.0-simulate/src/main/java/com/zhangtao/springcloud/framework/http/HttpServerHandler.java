package com.zhangtao.springcloud.framework.http;

import com.zhangtao.springcloud.framework.Invocation;
import com.zhangtao.springcloud.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1. @ClassDescription:   处理请求逻辑   dubbo 1、接口名 2、方法名 3、方法参数类型列表 4、方法的参数值列表 5、version版本号
 *      请求里就是一个： Invocation对象
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月23日 17:25
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp){
        //处理请求逻辑
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            //获取当前调用对象的接口名称，这个对象也仅仅只有接口名存在
            String interfaceName = invocation.getInterfaceName();
            //本地注册列表拿注册的实现类
            Class classImpl = LocalRegister.get(interfaceName);
            //反射执行方法
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParamType());
            String result = (String) method.invoke(classImpl.newInstance(), invocation.getParams());

            IOUtils.write(result,resp.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}