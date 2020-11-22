package com.czf.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class JsutNacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(JsutNacosApplication.class, args);
    }
}
