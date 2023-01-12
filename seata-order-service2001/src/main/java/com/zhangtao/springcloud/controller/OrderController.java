package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.FiOrder;
import com.zhangtao.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月26日 14:07
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/order/checkOrder/{productId}")
    public CommonResult checkOrder(@PathVariable("productId") String productId){
        FiOrder fiOrder = orderService.checkOrder(productId);
        return new CommonResult(200,"查询成功！",fiOrder);
    }

    @GetMapping(value = "/order/create")
    public CommonResult create(FiOrder fiOrder){

      /*  fiOrder.setOrderCount(10);
        fiOrder.setOrderStatus(0);
        fiOrder.setOrderTotalPrice(BigDecimal.valueOf(1000.00));
        fiOrder.setProductId("EC001");
        fiOrder.setProductName("化妆品");
        fiOrder.setUserId("us_001");*/

        orderService.create(fiOrder);
        return new CommonResult(200,"订单创建成功！");
    }

}