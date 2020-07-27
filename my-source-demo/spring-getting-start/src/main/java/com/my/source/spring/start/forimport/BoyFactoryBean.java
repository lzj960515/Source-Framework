package com.my.source.spring.start.forimport;

import org.springframework.beans.factory.FactoryBean;

/**
 * boy factory bean
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class BoyFactoryBean implements FactoryBean<Boy> {

    public BoyFactoryBean(){
        System.out.println("BoyFactoryBean was init!");
    }
    //返回Bean的对象
    @Override
    public Boy getObject() throws Exception {
        return new Boy();
    }

    //返回Bean的类型
    @Override
    public Class<?> getObjectType() {
        return Boy.class;
    }

    //是否单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
