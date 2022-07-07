package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * 更新用户Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 19:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "更新用户", description = "更新用户")
public class UserUpdateCommand extends Command {
    
    /**
     * 用户ID.
     */
    @ApiModelProperty(value = "用户ID")
    @NotEmpty(message = "用户ID不能为空")
    private String id;
    
    /**
     * 姓名.
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度不能超过50")
    private String userName;
    
    /**
     * 组织ID.
     */
    @ApiModelProperty(value = "组织ID")
    @NotEmpty(message = "组织不能为空")
    private String orgId;
    
    /**
     * 岗位ID.
     */
    @ApiModelProperty(value = "岗位ID")
    @NotEmpty(message = "岗位不能为空")
    private String stationId;
    
    /**
     * 邮箱.
     */
    @ApiModelProperty(value = "邮箱")
    @NotEmpty(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String email;
    
    /**
     * 手机.
     */
    @ApiModelProperty(value = "手机")
    @NotEmpty(message = "手机不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-1,4-9])|(15[0-3,5-9])|(16[2,5-7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$", message = "手机号格式不正确")
    private String mobile;
    
    /**
     * 性别 1：男 2 女 3 未知.
     */
    @ApiModelProperty(value = "性别")
    @NotNull(message = "性别不能为空")
    private Integer gender;
    
    /**
     * 头像.
     */
    @ApiModelProperty(value = "头像")
    @Length(max = 255, message = "头像长度不能超过255")
    private String avatar;
    
    /**
     * 工作描述.
     */
    @ApiModelProperty(value = "工作描述")
    @Length(max = 255, message = "工作描述长度不能超过255")
    private String workDescribe;
    
    /**
     * 上级领导.
     */
    @ApiModelProperty(value = "上级领导")
    private String superior;
    
    /**
     * 角色id集合
     */
    @ApiModelProperty(value = "角色id集合")
    private Set<String> roleIdList;
}
