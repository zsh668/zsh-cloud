package com.zsh.cloud.system.application.model.command;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zsh.cloud.common.core.dto.Command;
import com.zsh.cloud.common.web.excel.imports.ColumnHeader;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 批量导入用户Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/19 10:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "批量导入用户", description = "批量导入用户")
public class UserImportExcelCommand extends Command {
    
    /**
     * 账号.
     */
    @ColumnHeader(title = "账号", desc = "账号", example = "lisisi")
    @ExcelProperty(value = "账号", index = 0)
    private String account;
    
    /**
     * 姓名.
     */
    @ColumnHeader(title = "姓名", desc = "姓名", example = "李思思")
    @ExcelProperty(value = "姓名", index = 1)
    private String userName;
    
    /**
     * 组织.
     */
    @ColumnHeader(title = "组织", desc = "组织", example = "根组织")
    @ExcelProperty(value = "组织", index = 2)
    private String orgName;
    
    /**
     * 岗位.
     */
    @ColumnHeader(title = "岗位", desc = "岗位", example = "测试")
    @ExcelProperty(value = "岗位", index = 3)
    private String stationName;
    
    /**
     * 邮箱.
     */
    @ColumnHeader(title = "邮箱", desc = "邮箱", example = "lisisi@zsh6.com")
    @ExcelProperty(value = "邮箱", index = 4)
    private String email;
    
    /**
     * 手机.
     */
    @ColumnHeader(title = "手机", desc = "手机", example = "13112345678")
    @ExcelProperty(value = "手机", index = 5)
    private String mobile;
    
    /**
     * 性别.
     */
    @ColumnHeader(title = "性别", desc = "性别(男、女)", example = "女")
    @ExcelProperty(value = "性别", index = 6)
    private String gender;
    
    /**
     * 角色.
     */
    @ColumnHeader(title = "角色", desc = "角色(英文逗号分割)", notNull = false, example = "测试,管理员")
    @ExcelProperty(value = "角色", index = 7)
    private String roleNames;
    
    /**
     * 上级领导.
     */
    @ColumnHeader(title = "上级领导", notNull = false)
    @ExcelProperty(value = "上级领导", index = 8)
    private String superName;
    
    /**
     * 工作描述.
     */
    @ColumnHeader(title = "工作描述", notNull = false)
    @ExcelProperty(value = "工作描述", index = 9)
    private String workDescribe;
}
