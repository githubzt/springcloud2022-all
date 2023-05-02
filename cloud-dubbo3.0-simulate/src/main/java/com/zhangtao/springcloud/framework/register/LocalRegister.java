package com.zhangtao.springcloud.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. @ClassDescription: 本地注册
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月24日 17:55
 */
public class LocalRegister {

    private static Map<String,Class> map = new HashMap<>();

    public static void regist(String interfaceName,Class implClass){
        map.put(interfaceName,implClass);
    }

    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}