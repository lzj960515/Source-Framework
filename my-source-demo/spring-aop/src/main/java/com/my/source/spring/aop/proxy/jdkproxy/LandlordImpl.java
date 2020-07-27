package com.my.source.spring.aop.proxy.jdkproxy;

/**
 * 房东 实现类
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class LandlordImpl implements Landlord {
    @Override
    public boolean rent() {
        System.out.println("租房成功");
        return true;
    }

    @Override
    public boolean rentCollection() {
        System.out.println("收租成功");
        return true;
    }
}
