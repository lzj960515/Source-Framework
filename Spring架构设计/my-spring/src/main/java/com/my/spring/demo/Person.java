package com.my.spring.demo;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
public class Person {

    private final HongQiCar hongQiCar = new HongQiCar();

    public void drive(){
        hongQiCar.run();
    }
}