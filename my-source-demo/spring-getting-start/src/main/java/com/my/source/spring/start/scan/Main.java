package com.my.source.spring.start.scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 扫描方法 main
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
