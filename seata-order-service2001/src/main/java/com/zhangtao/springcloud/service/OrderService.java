package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.entities.FiOrder;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月26日 9:36
 */
public interface OrderService {

    //查询订单
    FiOrder checkOrder(String productId);
    //创建订单
    void create(FiOrder fiOrder);


}
