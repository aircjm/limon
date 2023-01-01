package com.aircjm.limon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.aircjm.limon.**.mapper")
public class LimonServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LimonServerApplication.class, args);
    }

}
