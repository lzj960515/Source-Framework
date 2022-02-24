package com.my.spring.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Component
public class Boy {

	// 使用Autowired注解表示car需要进行依赖注入
	@Autowired
	private Car car;

	public void driver(){
		car.run();
	}
}
