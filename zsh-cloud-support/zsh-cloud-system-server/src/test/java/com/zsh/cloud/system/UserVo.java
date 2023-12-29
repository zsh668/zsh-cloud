package com.zsh.cloud.system;

import com.zsh.cloud.common.web.excel.export.csv.ExcelName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangshuhang
 * @version 1.0
 * @date 2023/12/29 15:30
 */
@Data
@Accessors(chain = true)
public class UserVo {
    
    /**
     * 账号.
     */
    @ExcelName(name = "账号")
    private String account;
    
    /**
     * 姓名.
     */
    @ExcelName(name = "姓名")
    private String userName;
}
