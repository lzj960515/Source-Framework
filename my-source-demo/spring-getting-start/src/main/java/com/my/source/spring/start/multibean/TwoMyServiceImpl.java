package com.my.source.spring.start.multibean;

import org.springframework.stereotype.Service;

/**
 * 第二个
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Service
public class TwoMyServiceImpl implements MyService {
    @Override
    public void doService() {
        System.out.println("TwoServiceImpl.doService");
    }
}
