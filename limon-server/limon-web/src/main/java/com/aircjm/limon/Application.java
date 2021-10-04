package com.aircjm.limon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动程序
 *
 * @author aircjm
 */
@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("////////////////////////////////////////////////////////////////////\n" +
                "//                         项目启动 success                        //\n" +
                "///////////////////////////////////////////////////////////////////");
    }
}
