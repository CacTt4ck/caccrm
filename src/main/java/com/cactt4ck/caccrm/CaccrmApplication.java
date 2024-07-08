package com.cactt4ck.caccrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class CaccrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaccrmApplication.class, args);
    }

}
