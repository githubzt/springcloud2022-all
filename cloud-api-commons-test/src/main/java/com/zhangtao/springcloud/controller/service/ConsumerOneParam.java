package com.zhangtao.springcloud.controller.service;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月12日 13:38
 */
@FunctionalInterface
public interface ConsumerOneParam<T> {

    void accept(T t);
}
