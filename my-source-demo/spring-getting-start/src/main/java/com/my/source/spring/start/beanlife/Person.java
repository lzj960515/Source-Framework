package com.my.source.spring.start.beanlife;

import javafx.fxml.Initializable;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 通过实现接口测试bean生命周期
 *
 * @author Zijian Liao
 * @since 1.0
 */
public class Person implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person be born !");
    }

    public Person(){
        System.out.println("Person.Person");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Person die !");
    }
}
