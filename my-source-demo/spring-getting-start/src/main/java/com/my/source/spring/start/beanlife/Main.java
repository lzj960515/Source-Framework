package com.my.source.spring.start.beanlife;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * main
 *
 * @author Zijian Liao
 * @since
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//        context.removeBeanDefinition("people");
//        System.out.println(context.getBean("com.my.source.spring.start.beanlife.Person"));
//        context.removeBeanDefinition("person");
//        context.removeBeanDefinition("banana");
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//            context.removeBeanDefinition(beanDefinitionName);
//        }
    }
}
