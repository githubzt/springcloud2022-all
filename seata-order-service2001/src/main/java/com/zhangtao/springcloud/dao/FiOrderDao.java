package com.zhangtao.springcloud.dao;

import com.zhangtao.springcloud.entities.FiOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月23日 15:45
 */
@Mapper
public interface FiOrderDao {

    //查询订单
    FiOrder selectByProductId(String productId);

    //新建订单
    int create(FiOrder fiOrder);

    //修改订单状态
    void updateOrder(@Param("userId")String userid,@Param("orderStatus")Integer orderStatus);
}