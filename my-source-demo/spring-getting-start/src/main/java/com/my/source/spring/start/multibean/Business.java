package com.my.source.spring.start.multibean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 业务
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Component
public class Business {

    private final Map<String, MyService> serviceMap;

    public Business(Map<String, MyService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public void doBusiness(){
        serviceMap.forEach((key,service)->{
            service.doService();
        });
    }
}
