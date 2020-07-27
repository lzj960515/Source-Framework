package com.my.source.spring.aop.proxy.jdkproxy;

/**
 * 房东 接口类
 *
 * @author Zijian Liao
 * @since 1.0
 */
public interface Landlord {

    /**
     * 租房
     * @return {@code boolean}
     */
    boolean rent();

    /**
     * 收租
     * @return {@code boolean}
     */
    boolean rentCollection();
}
