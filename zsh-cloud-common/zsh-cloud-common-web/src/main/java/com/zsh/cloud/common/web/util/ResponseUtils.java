package com.zsh.cloud.common.web.util;

import com.zsh.cloud.common.core.util.JsonUtils;
import jodd.io.StreamUtil;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Objects;

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
    public static void writeObject(Object object) {
        writeJson(JsonUtils.toJsonString(object));
    }
    
    /**
     * response返回json
     */
    public static void writeJson(String jsonStr) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse response = Objects.requireNonNull(sra).getResponse();
        Objects.requireNonNull(response).setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        write(response, jsonStr);
    }
    
    /**
     * response写回数据.
     *
     * @param response
     * @param data
     */
    public static void write(HttpServletResponse response, String data) {
        try (PrintWriter writer = response.getWriter()) {
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("response输出响应失败", e);
        }
    }
    
    /**
     * 将文件写到response，供浏览器下载.
     *
     * @param file 文件
     * @param mime 文件类型
     */
    public static void flushFile(File file, MimeEnum mime) {
        flushFile(file, file.getName(), mime);
    }
    
    /**
     * 将文件写到response，供浏览器下载.
     *
     * @param file 文件
     * @param mime 文件类型
     */
    public static void flushFile(File file, String fileName, MimeEnum mime) {
        
        try (FileInputStream ios = new FileInputStream(file);) {
            
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletResponse response = Objects.requireNonNull(sra).getResponse();
            
            Objects.requireNonNull(response).setHeader("Set-Cookie", "fileDownload=true; path=/");
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            StreamUtil.copy(ios, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    
    /**
     * 将文件写到response中，供浏览器下载.
     *
     * @param fileName 文件名
     * @param mime     文件类型
     */
    public static void flushFile(String fileName, MimeEnum mime) {
        flushFile(new File(fileName), mime);
    }
    
    /**
     * 文件类型枚举.
     */
    @Getter
    public enum MimeEnum {
        
        XLS("xls", "application/x-excel"),
        XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
        ZIP("zip", "application/zip");
        
        private final String suffix;
        
        private final String contentType;
        
        MimeEnum(String suffix, String contentType) {
            this.suffix = suffix;
            this.contentType = contentType;
        }
    }
    
}
