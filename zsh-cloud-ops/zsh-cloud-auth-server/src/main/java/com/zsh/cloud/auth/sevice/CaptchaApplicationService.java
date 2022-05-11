package com.zsh.cloud.auth.sevice;

import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.BufferedImage;

/**
 * 验证码应用服务.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/19 18:20
 */
public interface CaptchaApplicationService {
    
    /**
     * 生成验证码.
     *
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);
    
    /**
     * 校验 验证码
     *
     * @param uuid
     * @param captchaCode
     * @return
     */
    boolean validateCaptcha(String uuid, String captchaCode);
}
