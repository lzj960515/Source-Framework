package com.my.source.spring.ioc.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * config
 *
 * @author Zijian Liao
 * @since 1.0
 */
@Import({MyApplicationListener.class})
@Configuration
public class MainConfig {
}
