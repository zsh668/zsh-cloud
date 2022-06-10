package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOptLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志 Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 14:39
 */
@Mapper
public interface SysOptLogMapper extends BaseMapperExt<SysOptLogDO> {

}
