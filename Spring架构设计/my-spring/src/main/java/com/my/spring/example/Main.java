package com.my.spring.example;


import com.my.spring.design.ApplicationContext;
import com.my.spring.design.ComponentScan;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@ComponentScan(basePackages = "com.my.spring.example")
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ApplicationContext(Main.class);
		Boy boy = (Boy) context.getBean("boy");
		boy.driver();
		System.out.println();
	}
}
