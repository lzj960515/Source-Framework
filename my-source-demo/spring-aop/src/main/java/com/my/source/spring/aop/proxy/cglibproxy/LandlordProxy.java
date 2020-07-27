package com.my.source.spring.aop.proxy.cglibproxy;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理类
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class LandlordProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("做点小动作");
        return methodProxy.invokeSuper(o, args);
    }
}
