package com.my.source.spring.start.scope;

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
        System.out.println(context.getBean("animal"));
        System.out.println(context.getBean("animal"));
        System.out.println(context.getBean("dog"));
        System.out.println(context.getBean("dog"));
    }
}
