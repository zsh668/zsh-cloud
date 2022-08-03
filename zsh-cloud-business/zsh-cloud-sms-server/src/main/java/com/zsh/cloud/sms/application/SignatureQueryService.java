package com.zsh.cloud.sms.application;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.sms.application.model.dto.SignatureDTO;
import com.zsh.cloud.sms.application.model.dto.SignaturePageDTO;
import com.zsh.cloud.sms.application.model.query.SignaturePageQuery;

import java.util.List;

/**
 * 签名查询服务接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:38
 */
public interface SignatureQueryService {
    
    /**
     * 分页查询.
     *
     * @param signaturePageQuery
     * @return
     */
    Page<SignaturePageDTO> queryPage(SignaturePageQuery signaturePageQuery);
    
    /**
     * 查询列表.
     *
     * @param signaturePageQuery
     * @return
     */
    List<SignaturePageDTO> queryList(SignaturePageQuery signaturePageQuery);
    
    /**
     * 查询.
     *
     * @param id
     * @return
     */
    SignatureDTO find(String id);
}
