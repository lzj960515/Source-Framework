package com.my.source.spring.ioc.listener;

import org.springframework.context.ApplicationEvent;
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
        context.publishEvent(new ApplicationEvent("publish an event") {
            @Override
            public Object getSource() {
                return "hello world!";
            }
        });

        context.getBean(MainConfig.class);
    }
}
