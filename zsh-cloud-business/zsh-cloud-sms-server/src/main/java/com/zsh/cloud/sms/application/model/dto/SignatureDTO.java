package com.zsh.cloud.sms.application.model.dto;

import com.zsh.cloud.common.core.dto.DTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 签名信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SignatureDTO", description = "签名信息")
public class SignatureDTO extends DTO {

}
