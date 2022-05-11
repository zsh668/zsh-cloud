package com.zsh.cloud.common.weixin.domain.msg;

import com.zsh.cloud.common.weixin.domain.base.BaseMessage;
import lombok.Data;

/**
 * 地理位置消息
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 15:28
 */
@Data
public class LocationMessage extends BaseMessage {
    
    /**
     * 地理位置纬度
     */
    private Double location_X;
    
    /**
     * 地理位置经度
     */
    private Double location_Y;
    
    /**
     * 地图缩放大小
     */
    private Double scale;
    
    /**
     * 地理位置信息
     */
    private String label;
}
