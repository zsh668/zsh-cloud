package com.zsh.cloud.common.weixin.domain.msg;

import com.zsh.cloud.common.weixin.domain.base.BaseMessage;
import lombok.Data;

/**
 * 链接消息
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:28
 */
@Data
public class LinkMessage extends BaseMessage {
    
    /**
     * 消息标题
     */
    private String title;
    
    /**
     * 消息描述
     */
    private String description;
    
    /**
     * 消息链接
     */
    private String url;
}
