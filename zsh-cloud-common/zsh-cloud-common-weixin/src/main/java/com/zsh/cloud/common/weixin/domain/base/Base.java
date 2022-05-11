package com.zsh.cloud.common.weixin.domain.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 公共的消息字段
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:19
 */
@Data
@ToString
public class Base implements Serializable {
    
    /**
     * 开发者微信号
     */
    private String ToUserName;
    
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    
    /**
     * 消息创建时间 （整型）
     */
    private Long CreateTime;
    
    /**
     * 消息类型
     */
    private String MsgType;
}
