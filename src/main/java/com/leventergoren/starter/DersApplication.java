package com.leventergoren.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.leventergoren")
@EntityScan(basePackages = "com.leventergoren")
@EnableJpaRepositories(basePackages = "com.leventergoren")
@SpringBootApplication
public class DersApplication {

    public static void main(String[] args) {
        SpringApplication.run(DersApplication.class, args);
    }

}
