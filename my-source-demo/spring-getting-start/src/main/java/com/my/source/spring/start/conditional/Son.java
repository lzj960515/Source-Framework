package com.my.source.spring.start.conditional;

/**
 * 子类
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Son {

    public Son(Father father){
        System.out.println("has father! " + father);
        System.out.println("Son was init!");
    }
}
