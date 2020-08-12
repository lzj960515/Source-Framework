package com.my.source.spring.aop.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试代理对象循环依赖问题
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Component
public class InstanceA implements Instance {

    @Autowired
    private Instance2 instanceB;

    @Override
    public String hello() {
        return "hello instance A";
    }
}
