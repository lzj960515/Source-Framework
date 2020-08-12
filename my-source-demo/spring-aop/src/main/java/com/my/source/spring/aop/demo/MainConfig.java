package com.my.source.spring.aop.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(basePackages = {"com.my.source.spring.aop.demo"})
public class MainConfig {

}
