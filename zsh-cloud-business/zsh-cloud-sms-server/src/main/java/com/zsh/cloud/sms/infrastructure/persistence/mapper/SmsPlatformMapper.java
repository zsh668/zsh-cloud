package com.zsh.cloud.sms.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.sms.infrastructure.persistence.entity.SmsPlatformDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接入平台表 Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Mapper
public interface SmsPlatformMapper extends BaseMapperExt<SmsPlatformDO> {

}
