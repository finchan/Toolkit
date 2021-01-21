package com.xavier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.xavier.stamps.mapper"})
@ComponentScan({"com.xavier.stamps.controller","com.xavier.stamps.service"})
public class ToolkitApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext =SpringApplication.run(ToolkitApplication.class, args);
    }

}
