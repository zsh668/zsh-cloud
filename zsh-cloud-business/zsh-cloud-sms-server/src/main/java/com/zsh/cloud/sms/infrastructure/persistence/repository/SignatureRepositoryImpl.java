package com.zsh.cloud.sms.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.sms.domain.model.signature.SignatureRepository;
import com.zsh.cloud.sms.infrastructure.persistence.entity.SmsSignatureDO;
import com.zsh.cloud.sms.infrastructure.persistence.mapper.SmsSignatureMapper;
import org.springframework.stereotype.Repository;

/**
 * 签名-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:58
 */
@Repository
public class SignatureRepositoryImpl extends ServiceImpl<SmsSignatureMapper, SmsSignatureDO>
        implements SignatureRepository, IService<SmsSignatureDO> {
    
}
