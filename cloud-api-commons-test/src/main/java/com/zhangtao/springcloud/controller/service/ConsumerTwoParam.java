package com.zhangtao.springcloud.controller.service;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月12日 13:52
 */
public interface ConsumerTwoParam<T> {

    int compare(T o1,T o2);
}
