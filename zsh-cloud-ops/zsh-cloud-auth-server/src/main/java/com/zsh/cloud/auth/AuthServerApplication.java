package com.zsh.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.zsh.cloud.auth")
public class AuthServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
    
}
