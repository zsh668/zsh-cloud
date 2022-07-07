package com.zsh.cloud.wx.application.model.query;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * 签名验证
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/14 13:34
 */
@Data
@ToString
public class WechatSignatureQuery {
    
    /**
     * 微信加密签名
     */
    @NotEmpty(message = "微信加密签名不能为空")
    private String signature;
    
    /**
     * 时间戳
     */
    @NotEmpty(message = "时间戳不能为空")
    private String timestamp;
    
    /**
     * 随机数
     */
    @NotEmpty(message = "随机数不能为空")
    private String nonce;
}
