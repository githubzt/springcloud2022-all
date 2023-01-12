package com.zhangtao.springcloud.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-zhangtao-springcloud-entities-FpStorage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FpStorage {
    /**
    * 主键id
    */
    @ApiModelProperty(value="主键id")
    private Long id;

    /**
    * 产品id
    */
    @ApiModelProperty(value="产品id")
    private String productId;

    /**
    * 总库存
    */
    @ApiModelProperty(value="总库存")
    private Integer productTotal;

    /**
    * 已用库存
    */
    @ApiModelProperty(value="已用库存")
    private Integer productUsed;

    /**
    * 剩余库存
    */
    @ApiModelProperty(value="剩余库存")
    private Integer productResidue;

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
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date operateTimeForThis;
}