package com.zsh.cloud.sms.application.model.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 签名分页信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SignaturePageDTO", description = "签名分页信息")
public class SignaturePageDTO extends DTO {
    
    /**
     * id.
     */
    private String id;
    
    /**
     * 签名名称.
     */
    private String signatureName;
    
    /**
     * 签名编码.
     */
    private String signatureCode;
    
    /**
     * 签名内容.
     */
    private String content;
    
    /**
     * 备注.
     */
    private String describe;
}
