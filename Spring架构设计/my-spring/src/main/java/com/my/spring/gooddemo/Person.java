package com.my.spring.gooddemo;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
public class Person {

    private final Car car;

    public Person(Car car){
        this.car = car;
    }

    public void drive(){
        car.run();
    }
}