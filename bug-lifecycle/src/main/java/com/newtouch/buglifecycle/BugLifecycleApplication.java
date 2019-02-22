package com.newtouch.buglifecycle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.newtouch")
@MapperScan({"com.newtouch.buglifecycle.dao"})
public class BugLifecycleApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BugLifecycleApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BugLifecycleApplication.class);
        springApplication.run(BugLifecycleApplication.class, args);
    }

}
