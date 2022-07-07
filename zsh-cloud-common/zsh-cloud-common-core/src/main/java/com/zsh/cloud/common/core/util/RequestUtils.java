package com.zsh.cloud.common.core.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zsh.cloud.common.core.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 请求工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/23 16:13
 */
@Slf4j
public class RequestUtils {
    
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    
    public static JSONObject getJwtPayload() {
        String jwtPayload = getRequest().getHeader(CommonConstant.JWT_PAYLOAD_KEY);
        try {
            jwtPayload = URLDecoder.decode(jwtPayload, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            log.error("转义异常", e);
        }
        JSONObject jsonObject = JSONUtil.parseObj(jwtPayload);
        return jsonObject;
    }
    
    public static String getUserId() {
        String userId = getJwtPayload().getStr(CommonConstant.USER_ID);
        return userId;
    }
    
    public static String getUserName() {
        String userName = getJwtPayload().getStr(CommonConstant.USER_NAME);
        return userName;
    }
}
