package com.zsh.cloud.sms.infrastructure.persistence.mapper;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.sms.application.model.query.SignaturePageQuery;
import com.zsh.cloud.sms.infrastructure.persistence.entity.SmsSignatureDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 签名表 Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/28 17:45
 */
@Mapper
public interface SmsSignatureMapper extends BaseMapperExt<SmsSignatureDO> {
    
    /**
     * 分页查询.
     *
     * @param query
     * @return
     */
    default Page<SmsSignatureDO> selectPage(SignaturePageQuery query) {
        return selectPage(query,
                Wraps.<SmsSignatureDO>lbQ().likeIfPresent(SmsSignatureDO::getSignatureName, query.getSignatureName())
                        .likeIfPresent(SmsSignatureDO::getSignatureCode, query.getSignatureCode())
                        .orderByDesc(SmsSignatureDO::getCreatedTime));
    }
}
