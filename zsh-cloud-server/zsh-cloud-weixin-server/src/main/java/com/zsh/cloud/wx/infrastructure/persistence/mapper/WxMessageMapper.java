package com.zsh.cloud.wx.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.cloud.wx.infrastructure.persistence.entity.WxMessageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公众号账号Mapper
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:42
 */
@Mapper
public interface WxMessageMapper extends BaseMapper<WxMessageDO> {

}
