package com.coderme.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qiudm
 * @date 2018/6/28 10:40
 * @desc
 */
@SpringBootApplication
@ComponentScan("com.coderme.*")
public class BlogApp {

    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class, args);
    }


}
