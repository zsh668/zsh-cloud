package com.zsh.cloud.common.core.util;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

/**
 * 根据ip查询地址.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/29 10:19
 */
@Slf4j
public class AddressUtil {
    
    private static final String JAVA_TEMP_DIR = "java.io.tmpdir";
    
    static Searcher searcher = null;
    
    static {
        try {
            String dbPath = Objects.requireNonNull(AddressUtil.class.getResource("/ip2region/ip2region.xdb")).getPath();
            File file = new File(dbPath);
            if (!file.exists()) {
                String tmpDir = System.getProperties().getProperty(JAVA_TEMP_DIR);
                dbPath = tmpDir + "ip2region.xdb";
                file = new File(dbPath);
                String classPath = "classpath:ip2region/ip2region.xdb";
                InputStream resourceAsStream = ResourceUtil.getStreamSafe(classPath);
                if (resourceAsStream != null) {
                    FileUtils.copyInputStreamToFile(resourceAsStream, file);
                }
            }
            // 1、从 dbPath 加载整个 xdb 到内存。
            byte[] cBuff = Searcher.loadContentFromFile(dbPath);
            // 2、使用上述的 cBuff 创建一个完全基于内存的查询对象。
            searcher = Searcher.newWithBuffer(cBuff);
        } catch (Exception e) {
            log.error("init ip region error:", e);
        }
    }
    
    /**
     * 解析IP.
     *
     * @param ip
     * @return
     */
    public static String getRegion(String ip) {
        try {
            //db
            if (searcher == null || StrUtil.isEmpty(ip)) {
                log.error("DbSearcher is null");
                return StrUtil.EMPTY;
            }
            long startTime = System.currentTimeMillis();
            String result = searcher.search(ip);
            long endTime = System.currentTimeMillis();
            log.debug("region use time[{}] result[{}]", endTime - startTime, result);
            return result;
            
        } catch (Exception e) {
            log.error("error", e);
        }
        return StrUtil.EMPTY;
    }
    
}
