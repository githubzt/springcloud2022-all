package com.zhangtao.springcloud.stream.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年04月07日 10:10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Apple {

    private int id;
    private String color;
    private BigDecimal weight;
    private String origin;

}