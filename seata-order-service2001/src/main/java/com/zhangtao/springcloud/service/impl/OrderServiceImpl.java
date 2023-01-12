package com.zhangtao.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhangtao.springcloud.dao.FiOrderDao;
import com.zhangtao.springcloud.entities.FiOrder;
import com.zhangtao.springcloud.service.AccountService;
import com.zhangtao.springcloud.service.OrderService;
import com.zhangtao.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月26日 9:38
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FiOrderDao fiOrderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    @Override
    public FiOrder checkOrder(String productId) {

        log.info("---------查询订单："+productId);
        FiOrder fiOrder = fiOrderDao.selectByProductId(productId);
        log.info("------订单查询结果： "+ JSONObject.toJSONString(fiOrder));
        return fiOrder;
    }

    @Override
    @GlobalTransactional(name = "create-order",rollbackFor = Exception.class)
    public void create(FiOrder fiOrder) {
        log.info("---------开始创建订单！");
        fiOrderDao.create(fiOrder);

        log.info("---------订单微服务开始调用库存，做扣减！");
        storageService.decrease(fiOrder.getProductId(),fiOrder.getOrderCount());
        log.info("---------订单微服务开始调用库存，做扣减end！");

        log.info("---------账户微服务开始调用余额，做余额扣减！");
        accountService.decrease(fiOrder.getUserId(),fiOrder.getOrderTotalPrice());
        log.info("---------账户微服务开始调用余额，做余额扣减end！");

        log.info("---------订单状态修改，0->1 完成！");
        fiOrderDao.updateOrder(fiOrder.getUserId(),0);
        log.info("---------订单创建完成！");
    }
}