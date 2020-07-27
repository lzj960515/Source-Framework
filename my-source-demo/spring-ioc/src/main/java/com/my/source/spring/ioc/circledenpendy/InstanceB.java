package com.my.source.spring.ioc.circledenpendy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * B类
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class InstanceB {
    private InstanceA instanceA;
    public void setInstanceA(InstanceA instanceA) {
        this.instanceA = instanceA;
    }
}
