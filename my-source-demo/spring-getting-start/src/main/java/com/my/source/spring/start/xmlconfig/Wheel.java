package com.my.source.spring.start.xmlconfig;

/**
 * 轮子，测试注入方式
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Wheel {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "name='" + name + '\'' +
                '}';
    }
}
