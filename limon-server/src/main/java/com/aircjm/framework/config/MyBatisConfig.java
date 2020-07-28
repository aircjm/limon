package com.aircjm.framework.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisConfig.
 *
 * @author johnniang
 */
@Configuration
@MapperScan(value = {"com.aircjm.project.system.mapper", "com.aircjm.project.project.mapper"})
public class MyBatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}