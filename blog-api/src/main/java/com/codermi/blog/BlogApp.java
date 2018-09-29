package com.codermi.blog;

import com.codermi.common.base.utils.PropertiesUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author qiudm
 * @date 2018/6/28 10:40
 * @desc
 */
@SpringBootApplication
@ComponentScan("com.codermi.*")
public class BlogApp implements EnvironmentAware {

    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class, args);
    }

    @Override
    public void setEnvironment(Environment environment) {
        PropertiesUtil.env = environment;
    }

    //master add 1
    //master add 2

    //==add1
    //==add2

}
