package com.zsh.cloud.auth;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/28 15:45
 */
public class Test {
    
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(passwordEncoder.matches("123456", "{bcrypt}$2a$10$c3bv6FzyjP7if2n8Zcu.I.dhEoWpEqn.Nn.Fy0EnnsyBWHR8nxDzq"));
    }
}
