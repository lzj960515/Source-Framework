package com.my.source.spring.start.annotationconfig;

import com.my.source.spring.start.annotationconfig.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Configuration
public class MainConfig {

    @Bean(value = "person")
    public Person person1(){
        return new Person();
    }
}
