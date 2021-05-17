package com.aircjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动程序
 *
 * @author aircjm
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("////////////////////////////////////////////////////////////////////\n" +
                "//                         项目启动成功                            //\n" +
                "////////////////////////////////////////////////////////////////////");
    }
}
