package com.my.source.spring.aop.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * config
 *
 * @author Zijian Liao
 * @since 1.0
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfig {

    @Bean
    public Calculator calculator(){
        return new MyCalculator();
    }

    @Bean
    public CalculatorAspect calculatorAspect(){
        return new CalculatorAspect();
    }
}
