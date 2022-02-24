package com.my.source.spring.aop.proxy;

import com.my.source.spring.aop.proxy.cglibproxy.CglibProxyFactory;
import com.my.source.spring.aop.proxy.cglibproxy.Landlord;
import com.my.source.spring.aop.proxy.cglibproxy.LandlordProxy;
import com.my.source.spring.aop.proxy.jdkproxy.LandlordImpl;
import com.my.source.spring.aop.proxy.jdkproxy.ProxyFactory;
import net.sf.cglib.core.DebuggingClassWriter;

import java.lang.reflect.Proxy;

/**
 * 测试代理
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/liaozijian");
//        Landlord landlord =  ProxyFactory.createProxyObject(new LandlordImpl(), Landlord.class, LandlordProxy.class);
        LandlordImpl landlordImpl = new LandlordImpl();
//        Landlord landlord = (Landlord) Proxy.newProxyInstance(LandlordImpl.class.getClassLoader(), LandlordImpl.class.getInterfaces(),new LandlordProxy(landlordImpl));
        Landlord landlord = (Landlord) CglibProxyFactory.newCglibProxy(new LandlordProxy(), Landlord.class);
        landlord.rent();
        landlord.rentCollection();

    }
}
