package com.zsh.cloud.sms.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.sms.application.SignatureQueryService;
import com.zsh.cloud.sms.application.model.dto.SignatureDTO;
import com.zsh.cloud.sms.application.model.dto.SignaturePageDTO;
import com.zsh.cloud.sms.application.model.query.SignaturePageQuery;
import com.zsh.cloud.sms.domain.model.signature.SignatureRepository;
import com.zsh.cloud.sms.infrastructure.persistence.mapper.SmsSignatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 签名查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:45
 */
@Service
public class SignatureQueryServiceImpl implements SignatureQueryService {
    
    @Autowired
    private SmsSignatureMapper smsSignatureMapper;
    
    @Autowired
    private SignatureRepository signatureRepository;
    
    @Override
    public Page<SignaturePageDTO> queryPage(SignaturePageQuery query) {
        smsSignatureMapper.selectPage(query);
        return null;
    }
    
    @Override
    public List<SignaturePageDTO> queryList(SignaturePageQuery signaturePageQuery) {
        return null;
    }
    
    @Override
    public SignatureDTO find(String id) {
        return null;
    }
}
