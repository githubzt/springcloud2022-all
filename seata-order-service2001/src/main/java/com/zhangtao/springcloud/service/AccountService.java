package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 1. @ClassDescription:  调用账户微服务，做金额扣减
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月26日 9:35
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {

    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId")String userId,@RequestParam("orderTotalPrice") BigDecimal orderTotalPrice);
}
