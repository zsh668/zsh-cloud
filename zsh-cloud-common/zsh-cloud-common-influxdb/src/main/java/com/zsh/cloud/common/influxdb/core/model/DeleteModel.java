package com.zsh.cloud.common.influxdb.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 14:05
 */
@Data
@NoArgsConstructor
public class DeleteModel extends BaseModel {
    
    public DeleteModel(String measurement) {
        super(measurement);
    }
}
