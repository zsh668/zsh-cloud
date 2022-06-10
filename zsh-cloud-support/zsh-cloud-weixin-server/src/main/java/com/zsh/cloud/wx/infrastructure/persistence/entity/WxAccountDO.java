package com.zsh.cloud.wx.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 公众号账号
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:39
 */
@Data
@TableName("wx_account")
public class WxAccountDO {
    
    @TableId(type = IdType.INPUT)
    private String appid;
    
    /**
     * 公众号名称
     */
    private String name;
    
    /**
     * 账号类型
     */
    private Integer type;
    
    /**
     * 认证状态
     */
    private String verified;
    
    /**
     * appsecret
     */
    private String secret;
    
    /**
     * token
     */
    private String token;
    
    /**
     * aesKey
     */
    private String aesKey;
}
