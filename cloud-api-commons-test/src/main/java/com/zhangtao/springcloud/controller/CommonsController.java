package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.FiOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 1. @ClassDescription: 测试swagger控制器
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月04日 12:48
 */
@RestController
@RequestMapping("/testCommons")
@Slf4j
@Api(value = "testCommons",tags = "测试swagger")
public class CommonsController {

    @ApiIgnore
    @GetMapping("/hello")
    public String hello(){
        return "Hello swagger!";
    }

    @ApiOperation(value = "测试swagger",notes = "打印信息")
    @GetMapping("/print/{message}")
    @ApiImplicitParam(name = "message",value = "请传递要打印的信息",required = true,dataType = "String",paramType = "path")
    public CommonResult print(@PathVariable String message){
        log.info("测试swagger打印信息:"+message);
        return  new CommonResult(200,"测试swagger打印信息: "+message);
    }

    @PostMapping("/printMessage")
    @ApiOperation(value = "测试swagger信息",notes = "打印实体信息（JSON格式）")
    public CommonResult<FiOrder> printMessage(@RequestBody FiOrder fiOrder){
        log.info("测试swagger打印实体信息,信息内容:"+fiOrder.getProductId()+";信息种类:"+fiOrder.getProductName());
        return new CommonResult<>(200,"测试swagger打印实体信息",fiOrder);
    }

    public String TestIdWork(){

        return "";
    }
}