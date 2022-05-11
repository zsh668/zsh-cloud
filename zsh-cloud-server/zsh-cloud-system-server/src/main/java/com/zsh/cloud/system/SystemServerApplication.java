package com.zsh.cloud.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.zsh.cloud.system")
public class SystemServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SystemServerApplication.class, args);
    }
    
}
