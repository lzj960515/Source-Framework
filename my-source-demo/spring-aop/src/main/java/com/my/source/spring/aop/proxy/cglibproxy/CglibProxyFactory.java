package com.my.source.spring.aop.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * cglib 工厂
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class CglibProxyFactory {

    public static Object newCglibProxy(LandlordProxy target, Class clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(target);
        return enhancer.create();
    }
}
