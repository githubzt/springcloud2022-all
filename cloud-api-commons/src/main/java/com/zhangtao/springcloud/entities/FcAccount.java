package com.zhangtao.springcloud.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="com-zhangtao-springcloud-entities-FcAccount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FcAccount {
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
     * 账户总金额
     */
    @ApiModelProperty(value="账户总金额")
    private BigDecimal acTotal;

    /**
     * 已使用金额
     */
    @ApiModelProperty(value="已使用金额")
    private BigDecimal acUsed;

    /**
     * 账户剩余金额
     */
    @ApiModelProperty(value="账户剩余金额")
    private BigDecimal acResidue;

    /**
     * 版本号(内部使用)
     */
    @ApiModelProperty(value="版本号(内部使用)")
    private Integer version;

    /**
     * 插入时间
     */
    @ApiModelProperty(value="插入时间")
    private Date insertTimeForHis;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private Date operateTimeForHis;
}