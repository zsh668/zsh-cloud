package com.zsh.cloud.common.weixin.domain.base;

import lombok.Data;
import lombok.ToString;

/**
 * 公共的消息字段
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:19
 */
@Data
@ToString(callSuper = true)
public class BaseMessage extends Base {
    
    /**
     * 消息id，64位整型
     */
    private Long MsgId;
}
