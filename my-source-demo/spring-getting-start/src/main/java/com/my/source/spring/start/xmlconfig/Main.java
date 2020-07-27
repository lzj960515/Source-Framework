package com.my.source.spring.start.xmlconfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml main
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(context.getBean("car"));
    }
}
