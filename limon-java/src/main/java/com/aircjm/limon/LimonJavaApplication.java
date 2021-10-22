package com.aircjm.limon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAspectJAutoProxy(exposeProxy = true)
@EnableAsync
@SpringBootApplication
@MapperScan("com.aircjm.limon.**.mapper")
public class LimonJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LimonJavaApplication.class, args);
    }

}
