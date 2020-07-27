package com.my.source.spring.start.beanlife;

/**
 * 测试bean的生命周期
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class People {

    public void init(){
        System.out.println("people be born !");
    }
    public People(){
        System.out.println("People.People");
    }
    public void destroy(){
        System.out.println("people die !");
    }
}
