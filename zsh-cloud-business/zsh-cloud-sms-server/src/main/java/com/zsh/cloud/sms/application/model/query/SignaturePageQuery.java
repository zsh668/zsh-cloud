package com.zsh.cloud.sms.application.model.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 签名分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("签名分页查询对象")
public class SignaturePageQuery extends PageQuery {
    
    /**
     * 签名名称.
     */
    @ApiModelProperty(value = "签名名称", notes = "模糊匹配")
    private String signatureName;
    
    /**
     * 签名编码.
     */
    @ApiModelProperty(value = "签名编码", notes = "模糊匹配")
    private String signatureCode;
}
