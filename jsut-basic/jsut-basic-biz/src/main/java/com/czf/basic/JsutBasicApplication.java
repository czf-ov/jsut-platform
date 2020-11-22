package com.czf.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class JsutBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsutBasicApplication.class, args);
    }
}
