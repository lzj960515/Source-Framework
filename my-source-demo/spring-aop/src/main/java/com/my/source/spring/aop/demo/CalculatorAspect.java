package com.my.source.spring.aop.demo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 计算器 切面
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Aspect
@Component
public class CalculatorAspect {

    @Pointcut(value = "execution(* com.my.source.spring.aop.demo.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void methodBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法 【" + methodName + "】 的【前置通知】，入参：" + Arrays.toString(joinPoint.getArgs()));
    }

    @After(value = "pointCut()")
    public void methodAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法 【" + methodName + "】 的【后置通知】，入参：" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointCut()")
    public void methodReturn(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法 【" + methodName + "】 的【返回通知】，入参：" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "pointCut()")
    public void methodThrow(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法 【" + methodName + "】 的【异常通知】，入参：" + Arrays.toString(joinPoint.getArgs()));
    }
}
