package com.my.spring.example;


import com.my.spring.design.Autowired;
import com.my.spring.design.Component;

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
