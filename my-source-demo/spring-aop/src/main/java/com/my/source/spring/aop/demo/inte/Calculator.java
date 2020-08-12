package com.my.source.spring.aop.demo.inte;

import java.math.BigDecimal;

/**
 * 计算器接口
 *
 * @author Zijian Liao
 * @since 1.0
 */
public interface Calculator {

    int add(int... nums);

    BigDecimal divide(int x, int y);

    int subtract(int x, int y);
}
