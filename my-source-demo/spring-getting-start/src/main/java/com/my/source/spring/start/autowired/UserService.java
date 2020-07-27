package com.my.source.spring.start.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String toString() {
        return "UserService{" +
                "userDao=" + userDao +
                '}';
    }
}
