package com.zsh.cloud.common.web.util;

import com.zsh.cloud.common.core.util.JsonUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Response工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Slf4j
@UtilityClass
public class ResponseUtils {
    
    /**
     * response返回Object
     */
    public static void writeObject(HttpServletResponse response, Object object) {
        writeJson(response, JsonUtils.toJsonString(object));
    }
    
    /**
     * response返回json
     */
    public static void writeJson(HttpServletResponse response, String jsonStr) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonStr);
            writer.flush();
        } catch (IOException e) {
            log.error("response输出json响应失败", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    /**
     * response写回数据
     */
    public static void write(HttpServletResponse response, String data) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("response输出响应失败", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    
}
