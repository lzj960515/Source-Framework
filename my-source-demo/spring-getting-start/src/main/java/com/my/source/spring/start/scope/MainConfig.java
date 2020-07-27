package com.my.source.spring.start.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * config
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Configuration
public class MainConfig {

    @Bean
    public Animal animal(){
        return new Animal();
    }

    @Bean
    @Scope(value = "prototype")
    public Dog dog(){
        return new Dog();
    }
}
