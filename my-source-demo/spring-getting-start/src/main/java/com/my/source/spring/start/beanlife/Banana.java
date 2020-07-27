package com.my.source.spring.start.beanlife;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * JSR250 测试bean生命周期
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Banana {

    @PostConstruct
    public void init(){
        System.out.println("Banana.init");
    }

    public Banana(){
        System.out.println("Banana.Banana");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Banana.destroy");
    }
}
