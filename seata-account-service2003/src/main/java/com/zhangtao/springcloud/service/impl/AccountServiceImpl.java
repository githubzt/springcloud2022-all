package com.zhangtao.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhangtao.springcloud.dao.FcAccountDao;
import com.zhangtao.springcloud.entities.FcAccount;
import com.zhangtao.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 13:50
 */
@Service
@Slf4j
public class AccountServiceImpl  implements AccountService {

    @Autowired
    private FcAccountDao fcAccountDao;


    @Override
    public FcAccount checkAccount(String userId) {
        log.info("-----查询账户余额：" + userId);
        FcAccount fcAccount = fcAccountDao.selectByUserId(userId);
        log.info("----账户查询结果： "+ JSONObject.toJSONString(fcAccount));
        return fcAccount;
    }

    @Override
    public void decrease(String userId, BigDecimal orderTotalPrice) {

        //查询账户余额
        log.info("----账户查询入参： "+ userId + "\t" + orderTotalPrice);
        FcAccount fcAccount1 = fcAccountDao.selectByUserId(userId);
        log.info("----账户查询结果： "+ JSONObject.toJSONString(fcAccount1));

        BigDecimal used = fcAccount1.getAcUsed().add(orderTotalPrice);
        BigDecimal resid = fcAccount1.getAcTotal().subtract(used);

        //模拟超时异常，全局事务回滚
/*        try{
            TimeUnit.SECONDS.sleep(20);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        //差额计算 降库存
        FcAccount fcAccount = new FcAccount();
        fcAccount.setUserId(userId);
        fcAccount.setAcUsed(used);
        fcAccount.setAcResidue(resid);
        fcAccountDao.updateUsed(fcAccount);
        log.info("----库存扣减完成! 余额：" + resid);
    }
}