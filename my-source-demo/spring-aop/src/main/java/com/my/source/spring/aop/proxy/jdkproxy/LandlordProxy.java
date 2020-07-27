package com.my.source.spring.aop.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 房东代理
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class LandlordProxy implements InvocationHandler {

    private Object target;

    public LandlordProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("rent".equals(method.getName())) {
            System.out.println("帮忙收取押金");
        }
        if("rentCollection".equals(method.getName())){
            System.out.println("帮忙收取租金");
        }
        return method.invoke(target,args);
    }
}
