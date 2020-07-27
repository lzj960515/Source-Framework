package com.my.source.spring.start.autowired;

import com.my.source.spring.start.scan.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * controller
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @Override
    public String toString() {
        return "UserController{" +
                "userService=" + userService +
                '}';
    }
}
