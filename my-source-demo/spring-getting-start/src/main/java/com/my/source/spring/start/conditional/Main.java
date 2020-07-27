package com.my.source.spring.start.conditional;

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
    }
}
