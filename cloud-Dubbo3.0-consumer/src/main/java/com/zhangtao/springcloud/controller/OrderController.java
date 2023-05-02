package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月16日 17:18
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String get(){
        //unary 方式
        String user = orderService.getOrder();
        return user;
    }

    @GetMapping("/orderstream")
    public String getByServerStream(){
        orderService.getOrderByServerStream();
        return "sucess";
    }

    @GetMapping("/orderclstream")
    public String getByClientStream(){
        String result = orderService.getOrderByClientStream();
        return result;
    }
}