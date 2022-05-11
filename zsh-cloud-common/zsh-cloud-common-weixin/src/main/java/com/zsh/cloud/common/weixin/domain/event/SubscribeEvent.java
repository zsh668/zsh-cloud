package com.zsh.cloud.common.weixin.domain.event;

import com.zsh.cloud.common.weixin.domain.base.BaseEvent;
import lombok.Data;
import lombok.ToString;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/16 16:56
 */
@Data
@ToString(callSuper = true)
public class SubscribeEvent extends BaseEvent {
    
    /**
     * 事件KEY值
     */
    private String eventKey;
}
