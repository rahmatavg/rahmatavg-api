package com.rahmatavg.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MyWebApplication {

    public static void main(String[] args) {
        // SpringApplication.run(MyWebApplication.class, args);
        SpringApplication app = new SpringApplication(MyWebApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "5000"));
        app.run(args);
    }

}
