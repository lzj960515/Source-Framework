package com.my.source.spring.start.scan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

/**
 * 扫描包 config
 *
 * @author Zijian Liao
 * @since 1.0
 */
@ComponentScan(basePackages = {"com.my.source.spring.start.scan"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {MyService.class})},
        //includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Repository.class})},
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, value = {MyTypeFilter.class})},
        useDefaultFilters = false)//为false表示只使用上面声明了的filter
@Configuration
public class MainConfig {
}
