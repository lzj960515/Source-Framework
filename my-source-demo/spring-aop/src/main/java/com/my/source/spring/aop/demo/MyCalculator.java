package com.my.source.spring.aop.demo;

import com.my.source.spring.aop.demo.inte.Calculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 计算器 实现类
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Component
public class MyCalculator implements Calculator {

    @Override
    public int add(int... nums) {
        System.out.println("MyCalculator.add");
        int r = 0;
        for (int num : nums) {
            r += num;
        }
        return r;
    }

    @Override
    public BigDecimal divide(int x, int y) {
        //System.out.println(1/0);
        return new BigDecimal(x/y);
    }

    @Override
    public int subtract(int x, int y) {
        return x - y;
    }
}
