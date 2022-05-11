package com.zsh.cloud.common.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zsh.cloud.common.core.util.RequestUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 通用参数填充实现类
 * <p></p>
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/23 15:42
 */
public class DefaultFieldHandler implements MetaObjectHandler {
    
    private static final String CREATED_TIME = "createdTime";
    
    private static final String CREATED_BY = "createdBy";
    
    private static final String UPDATED_TIME = "updatedTime";
    
    private static final String UPDATED_BY = "updatedBy";
    
    private String getCurrentUserId() {
        return Optional.ofNullable(RequestUtils.getUserId()).orElse("0");
    }
    
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CREATED_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, CREATED_BY, this::getCurrentUserId, String.class);
        this.strictUpdateFill(metaObject, UPDATED_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, UPDATED_BY, this::getCurrentUserId, String.class);
        //        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
        //            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();
        //
        //            LocalDateTime current = LocalDateTime.now();
        //            // 创建时间为空，则以当前时间为插入时间
        //            if (Objects.isNull(baseDO.getCreatedTime())) {
        //                baseDO.setCreatedTime(current);
        //            }
        //            // 更新时间为空，则以当前时间为更新时间
        //            if (Objects.isNull(baseDO.getUpdatedTime())) {
        //                baseDO.setUpdatedTime(current);
        //            }
        //
        //            String userId = RequestUtils.getUserId();
        //            // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
        //            if (StringUtils.hasText(userId) && Objects.isNull(baseDO.getCreatedBy())) {
        //                baseDO.setCreatedBy(userId);
        //            }
        //            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
        //            if (StringUtils.hasText(userId) && Objects.isNull(baseDO.getUpdatedBy())) {
        //                baseDO.setUpdatedBy(userId);
        //            }
        //        }
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATED_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, UPDATED_BY, this::getCurrentUserId, String.class);
        //        // 更新时间为空，则以当前时间为更新时间
        //        Object modifyTime = getFieldValByName("updatedTime", metaObject);
        //        if (Objects.isNull(modifyTime)) {
        //            setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
        //        }
        //
        //        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
        //        Object modifier = getFieldValByName("updatedBy", metaObject);
        //        String userId = RequestUtils.getUserId();
        //        if (StringUtils.hasText(userId) && Objects.isNull(modifier)) {
        //            setFieldValByName("updatedBy", userId, metaObject);
        //        }
    }
}
