package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.FcAccount;
import com.zhangtao.springcloud.entities.FpStorage;
import com.zhangtao.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 13:39
 */
@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/account/checkAccount/{userId}")
    public CommonResult checkAccount(@PathVariable("userId") String userId){
        FcAccount fcAccount = accountService.checkAccount(userId);
        return new CommonResult(200,"查询成功！",fcAccount);
    }

    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId")String userId, @RequestParam("orderTotalPrice") BigDecimal orderTotalPrice){
        accountService.decrease(userId,orderTotalPrice);
        return new CommonResult(200,"账户金额扣减成功！");
    }


}