package com.xavier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan(value={"com.xavier.*"})
public class ToolkitApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext =SpringApplication.run(ToolkitApplication.class, args);
    }

}
