package com.example.lms_admin_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class LmsAdminProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsAdminProjectApplication.class, args);
        System.out.println("--------------------------------");
        log.info("\nHello! Welcome to LMS Admin");
    }

}
