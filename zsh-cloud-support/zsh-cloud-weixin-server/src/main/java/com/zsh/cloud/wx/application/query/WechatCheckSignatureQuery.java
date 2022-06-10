package com.zsh.cloud.wx.application.query;

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
@ToString(callSuper = true)
public class WechatCheckSignatureQuery extends WechatSignatureQuery {
    
    /**
     * 随机字符串
     */
    @NotEmpty(message = "随机字符串不能为空")
    private String echostr;
}
