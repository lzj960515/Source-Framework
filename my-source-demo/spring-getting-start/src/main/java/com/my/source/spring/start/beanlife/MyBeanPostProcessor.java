package com.my.source.spring.start.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 自定义bean post processor
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在init方法前调用
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization ===== "+ beanName);
        return bean;
    }
    /**
     * 在init方法后调用
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessAfterInitialization ===== " + beanName);
        return bean;
    }
}

