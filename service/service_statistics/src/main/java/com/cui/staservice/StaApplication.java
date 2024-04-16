package com.cui.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author water
 * @EnableScheduling 开启定时任务
 * @date 2024/4/15
 * @Description
 */
@SpringBootApplication
@MapperScan("com.cui.staservice.mapper")
@ComponentScan("com.cui")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
