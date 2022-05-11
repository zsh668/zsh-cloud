package com.zsh.cloud.common.log.util;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 根据ip查询地址
 */
@Slf4j
public class AddressUtil {
    
    private static final String JAVA_TEMP_DIR = "java.io.tmpdir";
    
    static DbConfig config = null;
    
    static DbSearcher searcher = null;
    
    /**
     * 初始化IP库.
     */
    static {
        try {
            String dbPath = Objects.requireNonNull(AddressUtil.class.getResource("/ip2region/ip2region.db")).getPath();
            File file = new File(dbPath);
            if (!file.exists()) {
                String tmpDir = System.getProperties().getProperty(JAVA_TEMP_DIR);
                dbPath = tmpDir + "ip2region.db";
                file = new File(dbPath);
                String classPath = "classpath:ip2region/ip2region.db";
                InputStream resourceAsStream = ResourceUtil.getStreamSafe(classPath);
                if (resourceAsStream != null) {
                    FileUtils.copyInputStreamToFile(resourceAsStream, file);
                }
            }
            config = new DbConfig();
            searcher = new DbSearcher(config, dbPath);
            log.info("bean [{}]", config);
            log.info("bean [{}]", searcher);
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
            //查询算法
            Method method = searcher.getClass().getMethod("memorySearch", String.class);
            
            if (!Util.isIpAddress(ip)) {
                log.warn("warning: Invalid ip address");
            }
            DataBlock dataBlock = (DataBlock) method.invoke(searcher, ip);
            String result = dataBlock.getRegion();
            long endTime = System.currentTimeMillis();
            log.debug("region use time[{}] result[{}]", endTime - startTime, result);
            return result;
            
        } catch (Exception e) {
            log.error("error", e);
        }
        return StrUtil.EMPTY;
    }
    
}
