package com.my.source.spring.start.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

/**
 * main
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        SpringContextUtil springContextUtil = context.getBean("springContextUtil", SpringContextUtil.class);
        System.out.println(springContextUtil.applicationContext);
        System.out.println(context);

    }
}
