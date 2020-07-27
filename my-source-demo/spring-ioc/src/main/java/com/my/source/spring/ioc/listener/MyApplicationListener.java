package com.my.source.spring.ioc.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 自定义application listener
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class MyApplicationListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("my application listener on event" + event);
    }
}
