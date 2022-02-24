package com.my.spring.example;


import com.my.spring.design.Component;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
//@Component
public class GeelyCar implements Car {

	static {
		System.out.println("geely car was call");
	}

	@Override
	public void run() {
		System.out.println("geely car running");
	}
}
