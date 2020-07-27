package com.my.source.spring.start.property;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

/**
 * 测试属性注解
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Student {
    @Value("${name}")
    private String name;
    @Value("${age}")
    private int age;
    @Value("${score}")
    private BigDecimal score;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
