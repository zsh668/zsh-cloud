package com.zsh.cloud.system.application.model.dto;

import com.zsh.cloud.common.core.dto.DTO;
import com.zsh.cloud.common.web.translate.Translate;
import com.zsh.cloud.common.web.translate.TranslateField;
import com.zsh.cloud.system.application.translate.ServiceImplTranslator;
import com.zsh.cloud.system.domain.model.user.GenderEnum;
import com.zsh.cloud.system.infrastructure.persistence.repository.StationRepositoryImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户层次结构DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/8 10:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "HierarchyDTO", description = "用户层次结构")
public class HierarchyDTO extends DTO {
    
    private boolean expand = true;
    
    @ApiModelProperty(value = "自身节点")
    private boolean self;
    
    @ApiModelProperty(value = "上级领导")
    private String superior;
    
    @ApiModelProperty(value = "姓名")
    private String userName;
    
    @ApiModelProperty(value = "岗位ID")
    private String stationId;
    
    @ApiModelProperty(value = "岗位名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = StationRepositoryImpl.class, from = "stationId", param = "stationName")
    private String stationName;
    
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
    
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    @ApiModelProperty(value = "子集")
    @TranslateField
    private List<HierarchyDTO> children;
}
