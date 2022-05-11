package com.zsh.cloud.wx.application.query;

import lombok.Data;
import lombok.ToString;

/**
 * 消息
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/14 13:34
 */
@Data
@ToString(callSuper = true)
public class WechatMessageQuery extends WechatSignatureQuery {
    
    /**
     * 发送方帐号
     */
    private String openid;
    
    /**
     * 加密方式
     */
    private String encrypt_type;
    
    /**
     * 消息加密签名
     */
    private String msg_signature;
}
