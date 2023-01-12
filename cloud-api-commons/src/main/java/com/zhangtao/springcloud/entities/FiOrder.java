package com.zhangtao.springcloud.entities;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-zhangtao-springcloud-domain-FiOrder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiOrder {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private String userId;

    /**
     * 产品id
     */
    @ApiModelProperty(value="产品id")
    private String productId;

    /**
     * 产品名称
     */
    @ApiModelProperty(value="产品名称")
    private String productName;

    /**
     * 产品数量
     */
    @ApiModelProperty(value="产品数量")
    private Integer orderCount;

    /**
     * 商品总价
     */
    @ApiModelProperty(value="商品总价")
    private BigDecimal orderTotalPrice;

    /**
     * 订单状态0创建中，1已创建
     */
    @ApiModelProperty(value="订单状态0创建中，1已创建")
    private Integer orderStatus;

    /**
     * 版本号(内部使用)
     */
    @ApiModelProperty(value="版本号(内部使用)")
    private Integer version;

    /**
     * 插入时间
     */
    @ApiModelProperty(value="插入时间")
    private Date insertTimeForThis;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private Date operateTimeForThis;
}
