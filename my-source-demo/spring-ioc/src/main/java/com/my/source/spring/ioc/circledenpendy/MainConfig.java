package com.my.source.spring.ioc.circledenpendy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * config
 *
 * @author Zijian Liao
 * @since 1.0
 */
//@ComponentScan(basePackages = {"com.my.source.spring.ioc.circledenpendy"})
@Configuration
@ImportResource(locations = "classpath:beans.xml")
public class MainConfig {
}
