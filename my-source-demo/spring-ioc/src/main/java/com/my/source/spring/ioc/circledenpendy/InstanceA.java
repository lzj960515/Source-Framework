package com.my.source.spring.ioc.circledenpendy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试循环依赖 A类
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class InstanceA {

    private InstanceB instanceB;
    public void setInstanceB(InstanceB instanceB) {
        this.instanceB = instanceB;
    }
}
