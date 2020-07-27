package com.my.source.spring.start.forimport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * config
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Import({Man.class, MyImport.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig {

    @Bean
    public BoyFactoryBean boyFactoryBean(){
        return new BoyFactoryBean();
    }
}
