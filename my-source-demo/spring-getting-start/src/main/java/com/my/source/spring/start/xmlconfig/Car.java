package com.my.source.spring.start.xmlconfig;

/**
 * car 用于测试xml配置方式
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Car {

    private String name;

    private String brand;

    private Wheel wheel;

    public Car(String brand, Wheel wheel){
        this.brand = brand;
        this.wheel = wheel;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
