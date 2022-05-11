package com.zsh.cloud.common.weixin.domain.msg;

import com.zsh.cloud.common.weixin.domain.base.BaseMessage;
import lombok.Data;
import lombok.ToString;

/**
 * 文本消息
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:28
 */
@Data
@ToString(callSuper = true)
public class TextMessage extends BaseMessage {
    
    /**
     * 文本消息内容
     */
    private String Content;
}
