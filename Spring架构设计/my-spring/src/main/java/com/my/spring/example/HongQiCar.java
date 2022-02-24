package com.my.spring.example;

import com.my.spring.design.Component;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Component
public class HongQiCar implements Car {

	@Override
	public void run() {
		System.out.println("hongqi car running");
	}
}
