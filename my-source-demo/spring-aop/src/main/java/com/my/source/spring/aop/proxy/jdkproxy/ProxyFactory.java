package com.my.source.spring.aop.proxy.jdkproxy;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class ProxyFactory {

    public static <T> T  createProxyObject(Object target, Class<T> clazz, Class<? extends InvocationHandler> proxy) throws Exception {
        Constructor<? extends InvocationHandler> constructor = proxy.getConstructor(Object.class);
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), constructor.newInstance(target));
    }
}
