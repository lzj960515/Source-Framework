package com.my.source.spring.aop.demo;

import com.my.source.spring.aop.demo.inte.Calculator;
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
        Instance instanceA = (Instance) context.getBean("instanceA");
        Instance2 instanceB = (Instance2) context.getBean("instanceB");
        Calculator calculator = (Calculator) context.getBean("myCalculator");
        BigDecimal result = calculator.divide( 6, 3);
        System.out.println(result);

    }
}
