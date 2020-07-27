package com.my.source.spring.start.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * config
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Configuration
public class MainConfig {

//    @Bean
    public Father father(){
        return new Father();
    }

    @Bean
    @Conditional(MyCondition.class)
    public Son son(Father father){
        return new Son(father);
    }
}
