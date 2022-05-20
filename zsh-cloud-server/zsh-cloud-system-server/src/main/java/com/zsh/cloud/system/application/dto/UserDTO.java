package com.zsh.cloud.system.application.dto;

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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 15:26
 */
@ApiModel(value = "UserDTO", description = "用户信息")
@Data
public class UserDTO implements Serializable {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "用户ID")
    private String id;
    
    /**
     * 账号.
     */
    @ApiModelProperty(value = "账号")
    private String account;
    
    /**
     * 姓名.
     */
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
    @ApiModelProperty(value = "组织名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = OrgRepositoryImpl.class, from = "orgId")
    private String orgName;
    
    /**
     * 岗位ID.
     */
    @ApiModelProperty(value = "岗位ID")
    private String stationId;
    
    /**
     * 岗位名称.
     */
    @ApiModelProperty(value = "岗位名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = StationRepositoryImpl.class, from = "stationId")
    private String stationName;
    
    /**
     * 邮箱.
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    /**
     * 手机.
     */
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
    @ApiModelProperty(value = "状态")
    @Translate(dataSource = StatusEnum.class, from = "status")
    private String statusDesc;
    
    /**
     * 头像.
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    /**
     * 工作描述.
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
    @ApiModelProperty(value = "上级领导")
    @Translate(translator = ServiceImplTranslator.class, dataSource = UserRepositoryImpl.class, from = "superior")
    private String superiorName;
    
    /**
     * 角色id集合.
     */
    @ApiModelProperty(value = "角色id集合")
    private List<String> roleIdList;
    
    /**
     * 角色名称集合.
     */
    @ApiModelProperty(value = "角色名称集合")
    private List<String> roleNameList;
    
    /**
     * 用户组名称.
     */
    @ApiModelProperty(value = "用户组名称")
    private List<String> userGroupsNames;
}
