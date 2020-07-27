package com.my.source.spring.start.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * config
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Configuration
@PropertySource(value = {"classpath:student.properties"} , encoding = "UTF-8")
public class MainConfig {

    @Bean
    public Student student(){
        return new Student();
    }
}
