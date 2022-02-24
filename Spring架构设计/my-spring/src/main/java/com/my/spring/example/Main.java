package com.my.spring.test.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
// 告诉Spring从哪个包下扫描Bean，不写就是当前包路径
@ComponentScan(basePackages = "com.my.spring.test.demo")
public class Main {

	public static void main(String[] args) {
		// 将Main(配置信息)传入到ApplicationContext(IoC容器)中
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		// 从(IoC容器)中获取到我们的boy
		Boy boy = (Boy) context.getBean("boy");
		// 开车
		boy.driver();
	}
}
