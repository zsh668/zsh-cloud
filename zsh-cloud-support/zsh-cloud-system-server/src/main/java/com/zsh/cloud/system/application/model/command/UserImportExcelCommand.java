package com.zsh.cloud.system.application.model.command;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zsh.cloud.common.core.dto.Command;
import com.zsh.cloud.common.web.excel.imports.ColumnHeader;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @NotEmpty(message = "账号不能为空")
    @Length(max = 30, message = "账号长度不能超过30")
    private String account;
    
    /**
     * 姓名.
     */
    @ColumnHeader(title = "姓名", desc = "姓名", example = "李思思")
    @ExcelProperty(value = "姓名", index = 1)
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度不能超过50")
    private String userName;
    
    /**
     * 组织.
     */
    @ColumnHeader(title = "组织", desc = "组织", example = "根组织")
    @ExcelProperty(value = "组织", index = 2)
    @NotEmpty(message = "组织不能为空")
    private String orgName;
    
    /**
     * 岗位.
     */
    @ColumnHeader(title = "岗位", desc = "岗位", example = "测试")
    @ExcelProperty(value = "岗位", index = 3)
    @NotEmpty(message = "岗位不能为空")
    private String stationName;
    
    /**
     * 邮箱.
     */
    @ColumnHeader(title = "邮箱", desc = "邮箱", example = "lisisi@zsh6.com")
    @ExcelProperty(value = "邮箱", index = 4)
    @NotEmpty(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;
    
    /**
     * 手机.
     */
    @ColumnHeader(title = "手机", desc = "手机", example = "13112345678")
    @ExcelProperty(value = "手机", index = 5)
    @NotEmpty(message = "手机不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-1,4-9])|(15[0-3,5-9])|(16[2,5-7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$", message = "手机号格式不正确")
    private String mobile;
    
    /**
     * 性别.
     */
    @ColumnHeader(title = "性别", desc = "性别(男、女)", example = "女")
    @ExcelProperty(value = "性别", index = 6)
    @NotEmpty(message = "性别不能为空")
    private String genderDesc;
    
    /**
     * 角色.
     */
    @ColumnHeader(title = "角色", desc = "角色(英文逗号分割)", notNull = false, example = "测试,管理员")
    @ExcelProperty(value = "角色", index = 7)
    private String roleNames;
}
