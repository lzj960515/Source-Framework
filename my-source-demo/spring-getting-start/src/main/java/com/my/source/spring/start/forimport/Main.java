package com.my.source.spring.start.forimport;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * main
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//        System.out.println(context.getBean("my-baby"));
        System.out.println(context.getBean("boyFactoryBean"));
        System.out.println(context.getBean("boyFactoryBean"));
    }
}
