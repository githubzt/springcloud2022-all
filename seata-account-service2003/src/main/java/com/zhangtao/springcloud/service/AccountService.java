package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.entities.FcAccount;

import java.math.BigDecimal;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 13:50
 */
public interface AccountService {

    //查询账户
    FcAccount checkAccount(String userId);
    //余额扣减
    public void decrease(String userId,BigDecimal orderTotalPrice);

}