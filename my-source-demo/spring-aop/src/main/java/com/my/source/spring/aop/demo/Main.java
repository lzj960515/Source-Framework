package com.my.source.spring.aop.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

/**
 * main
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Calculator calculator = (Calculator) context.getBean("calculator");
        BigDecimal result = calculator.divide( 6, 3);
        System.out.println(result);
    }
}
