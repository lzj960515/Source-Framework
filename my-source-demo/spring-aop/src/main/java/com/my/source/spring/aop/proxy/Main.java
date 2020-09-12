package com.my.source.spring.aop.proxy;

import com.my.source.spring.aop.proxy.cglibproxy.CglibProxyFactory;
import com.my.source.spring.aop.proxy.jdkproxy.Landlord;
import com.my.source.spring.aop.proxy.jdkproxy.LandlordImpl;
import com.my.source.spring.aop.proxy.jdkproxy.LandlordProxy;
import com.my.source.spring.aop.proxy.jdkproxy.ProxyFactory;

import java.lang.reflect.Proxy;

/**
 * 测试代理
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        Landlord landlord =  ProxyFactory.createProxyObject(new LandlordImpl(), Landlord.class, LandlordProxy.class);
        LandlordImpl landlordImpl = new LandlordImpl();
        Landlord landlord = (Landlord) Proxy.newProxyInstance(LandlordImpl.class.getClassLoader(), LandlordImpl.class.getInterfaces(),new LandlordProxy(landlordImpl));
//        Landlord landlord = (Landlord) CglibProxyFactory.newCglibProxy(new LandlordProxy(), Landlord.class);
        landlord.rent();
        landlord.rentCollection();

    }
}
