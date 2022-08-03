package com.zsh.cloud.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.zsh.cloud")
public class SmsServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmsServerApplication.class, args);
    }
    
}
