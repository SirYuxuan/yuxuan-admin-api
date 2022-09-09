package com.yuxuan66.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Indexed;

/**
 * @author Sir丶雨轩
 * @since 2022/9/8
 */
@Indexed
@SpringBootApplication
public class AdminApp {

    public static void main(String... args) {
        SpringApplication.run(AdminApp.class, args);
    }
}
