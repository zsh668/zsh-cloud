package com.zsh.cloud.common.core.constant;

/**
 * 权限常量.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 11:12
 */
public interface AuthConstants {
    
    /**
     * JWT存储权限前缀.
     */
    String AUTHORITY_PREFIX = "ROLE_";
    
    /**
     * JWT存储权限属性.
     */
    String JWT_AUTHORITIES_KEY = "authorities";
    
    /**
     * 认证请求头key.
     */
    String AUTHORIZATION_KEY = "Authorization";
    
    /**
     * JWT令牌前缀.
     */
    String AUTHORIZATION_PREFIX = "Bearer ";
    
    /**
     * JWT载体key.
     */
    String JWT_PAYLOAD_KEY = "payload";
    
    /**
     * JWT ID 唯一标识.
     */
    String JWT_JTI = "jti";
    
    /**
     * JWT ID 唯一标识.
     */
    String JWT_EXP = "exp";
    
    /**
     * 黑名单token前缀.
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";
    
    /**
     * 查询字段.
     */
    String CLIENT_DETAILS_FIELDS = "client_id, client_secret, resource_ids, scope, authorized_grant_types, "
            + "web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";
    
    /**
     * 查询.
     */
    String BASE_CLIENT_DETAILS_SQL = "select " + CLIENT_DETAILS_FIELDS + " from sys_oauth_client_details";
    
    /**
     * 查询排序.
     */
    String FIND_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " order by client_id";
    
    /**
     * 带条件查询.
     */
    String SELECT_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " where client_id = ?";
    
    /**
     * 用户ID.
     */
    String USER_ID_KEY = "userId";
    
    /**
     * 用户名.
     */
    String USER_NAME_KEY = "userName";
    
    /**
     * 租户ID.
     */
    String TENANT_ID_KEY = "tenantId";
    
    /**
     * 认证地址.
     */
    String OAUTH_TOKEN = "/oauth/token";
    
    /**
     * 授权类型.
     */
    String GRANT_TYPE_KEY = "grant_type";
    
    /**
     * 重刷token.
     */
    String REFRESH_TOKEN = "refresh_token";
    
    /**
     * 密码.
     */
    String PASSWORD = "password";
    
    /**
     * 密码加密方式.
     */
    String BCRYPT = "{bcrypt}";
    
    /**
     * 验证码.
     */
    String VALIDATE_CODE_CODE = "code";
    
    /**
     * 验证码 key.
     */
    String VALIDATE_CODE_KEY = "key";
}
