package com.zsh.cloud.common.web.excel.imports;

import com.zsh.cloud.common.core.dto.DTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * 导入返回值.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/19 17:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ImportResultDTO extends DTO {
    
    /**
     * 总数.
     */
    private Integer total = 0;
    
    /**
     * 成功数.
     */
    private Integer success;
    
    /**
     * 失败数.
     */
    private Integer fail;
    
    /**
     * 错误信息.
     */
    private Collection<String> message;
}
