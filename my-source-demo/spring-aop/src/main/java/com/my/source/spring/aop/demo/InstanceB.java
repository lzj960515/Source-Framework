package com.my.source.spring.aop.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * b
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Component
public class InstanceB implements Instance2 {

    @Autowired
    private Instance instanceA;

    @Override
    public String hello() {
        return "hello instance B";
    }
}
