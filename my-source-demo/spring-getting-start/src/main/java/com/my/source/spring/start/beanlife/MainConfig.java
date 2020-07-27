package com.my.source.spring.start.beanlife;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * config
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Configuration
@Import({Person.class, Banana.class})
public class MainConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public People people() {
        return new People();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }
}
