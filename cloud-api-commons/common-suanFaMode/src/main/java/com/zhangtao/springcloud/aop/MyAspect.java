package com.zhangtao.springcloud.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月06日 9:58
 */
@Aspect
@Component
public class MyAspect {

    @Before("execution(public int com.zhangtao.springcloud.aop.CalcServiceImpl.*(..))")
    public void beforeNotify(){
        System.out.println("******** @Before我是前置通知MyAspect");
    }
    @After("execution(public int com.zhangtao.springcloud.aop.CalcServiceImpl.*(..))")
    public void afterNotify(){
        System.out.println("******** @After我是后置通知MyAspect");
    }

    @AfterReturning("execution(public int com.zhangtao.springcloud.aop.CalcServiceImpl.*(..))")
    public void afterReturningNotify(){
        System.out.println("******** @AfterReturning我是返回后通知MyAspect");
    }

    @AfterThrowing("execution(public int com.zhangtao.springcloud.aop.CalcServiceImpl.*(..))")
    public void afterThrowingNotify(){
        System.out.println("******** @AfterThrowingr我是异常通知MyAspect");
    }

    @Around("execution(public int com.zhangtao.springcloud.aop.CalcServiceImpl.*(..))")
    public Object afterAroundNotify(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object retValue = null;
        System.out.println("@Around我是环绕之前通知MyAspect");
        retValue = proceedingJoinPoint.proceed();
        System.out.println("@Around我是环绕之后通知MyAspect");
        return retValue;
    }


}