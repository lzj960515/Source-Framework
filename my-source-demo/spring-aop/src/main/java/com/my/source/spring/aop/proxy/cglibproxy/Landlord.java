package com.my.source.spring.aop.proxy.cglibproxy;

/**
 * 房东 父类
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Landlord {

    public boolean rent() {
        System.out.println("租房成功");
        return true;
    }

    public boolean rentCollection() {
        System.out.println("收租成功");
        return true;
    }
}
