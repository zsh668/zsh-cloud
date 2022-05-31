package com.zsh.cloud.system.application.dto;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.localdatetime.LocalDateTimeStringConverter;
import com.zsh.cloud.common.core.dto.DTO;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.web.translate.Translate;
import com.zsh.cloud.system.application.translate.ServiceImplTranslator;
import com.zsh.cloud.system.domain.model.user.GenderEnum;
import com.zsh.cloud.system.infrastructure.persistence.repository.OrgRepositoryImpl;
import com.zsh.cloud.system.infrastructure.persistence.repository.StationRepositoryImpl;
import com.zsh.cloud.system.infrastructure.persistence.repository.UserRepositoryImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户分页DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 15:44
 */
@ApiModel(value = "UserPageDTO", description = "用户分页信息")
@Data
@ExcelIgnoreUnannotated
public class UserPageDTO extends DTO {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "用户ID")
    private String id;
    
    /**
     * 账号.
     */
    @ExcelProperty("账号")
    @ApiModelProperty(value = "账号")
    private String account;
    
    /**
     * 姓名.
     */
    @ExcelProperty("姓名")
    @ApiModelProperty(value = "姓名")
    private String userName;
    
    /**
     * 组织ID.
     */
    @ApiModelProperty(value = "组织ID")
    private String orgId;
    
    /**
     * 组织名称.
     */
    @ExcelProperty("组织名称")
    @ApiModelProperty(value = "组织名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = OrgRepositoryImpl.class, from = "orgId", param = "orgName")
    private String orgName;
    
    /**
     * 岗位ID.
     */
    @ApiModelProperty(value = "岗位ID")
    private String stationId;
    
    /**
     * 岗位名称.
     */
    @ExcelProperty("岗位名称")
    @ApiModelProperty(value = "岗位名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = StationRepositoryImpl.class, from = "stationId", param = "stationName")
    private String stationName;
    
    /**
     * 邮箱.
     */
    @ExcelProperty("邮箱")
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    /**
     * 手机.
     */
    @ExcelProperty("手机")
    @ApiModelProperty(value = "手机")
    private String mobile;
    
    /**
     * 性别 1：男 2 女 3 未知.
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;
    
    /**
     * 性别 1：男 2 女 3 未知.
     */
    @ExcelProperty("性别")
    @ApiModelProperty(value = "性别")
    @Translate(dataSource = GenderEnum.class, from = "gender")
    private String genderDesc;
    
    /**
     * 状态 1启用 0禁用.
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    
    /**
     * 状态 1启用 0禁用.
     */
    @ExcelProperty("状态")
    @ApiModelProperty(value = "状态")
    @Translate(dataSource = StatusEnum.class, from = "status")
    private String statusDesc;
    
    /**
     * 头像.
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    /**
     * 工作描述 比如：市长、管理员、局长等等用于登陆展示.
     */
    @ApiModelProperty(value = "工作描述")
    private String workDescribe;
    
    /**
     * 密码过期时间.
     */
    @ApiModelProperty(value = "密码过期时间")
    private LocalDateTime passwordExpireTime;
    
    /**
     * 最后登录时间.
     */
    @ExcelProperty(value = "最后登录时间", converter = LocalDateTimeStringConverter.class)
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;
    
    /**
     * 上级领导.
     */
    @ApiModelProperty(value = "上级领导")
    private String superior;
    
    /**
     * 上级领导.
     */
    @ExcelProperty("上级领导")
    @ApiModelProperty(value = "上级领导")
    @Translate(translator = ServiceImplTranslator.class, dataSource = UserRepositoryImpl.class, from = "superior", param = "userName")
    private String superiorName;
}


