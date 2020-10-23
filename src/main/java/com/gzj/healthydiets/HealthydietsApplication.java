package com.gzj.healthydiets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan(value = "com.gzj.healthydiets.dao")
public class HealthydietsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthydietsApplication.class, args);
    }
}
