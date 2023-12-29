package com.zsh.cloud.common.web.excel.export.csv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * csv导出工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2023/12/29 15:01
 */
@Slf4j
public class CsvFileUtil {
    
    public static final String FILE_SUFFIX = ".csv";
    
    public static final String CSV_DELIMITER = ",";
    
    public static final String CSV_TAIL = "\r\n";
    
    protected static final String DATE_STR_FILE_NAME = "yyyyMMddHHmmssSSS";
    
    /**
     * 写文件
     *
     * @param fileName
     * @param content
     */
    public static void writeFile(String fileName, String content) {
        try (FileOutputStream fos = new FileOutputStream(fileName,
                true); OutputStreamWriter writer = new OutputStreamWriter(fos, "GBK");) {
            writer.write(content);
            writer.flush();
        } catch (Exception e) {
            log.error("写文件异常", e);
        }
    }
    
    /**
     * 构建文件名称
     *
     * @param fileName
     * @return
     */
    public static String buildCsvFileFileName(String fileName) {
        return fileName + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_STR_FILE_NAME))
                + FILE_SUFFIX;
    }
    
    /**
     * 构建excel内容
     *
     * @param dataLists
     * @return
     */
    public static String buildCsvFile(List<?> dataLists) {
        StringBuilder lineBuilder = new StringBuilder();
        // 头部信息
        lineBuilder.append(buildCsvFileTableNames(dataLists.get(0)));
        // 数据
        lineBuilder.append(buildCsvFileBodyMap(dataLists));
        return lineBuilder.toString();
    }
    
    /**
     * 构建excel 标题行名
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String buildCsvFileTableNames(T entity) {
        StringBuilder tableNames = new StringBuilder();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (!"serialVersionUID".equals(field.getName())) {
                    String tableTitleName = field.getName();
                    ExcelName myFieldAnn = field.getAnnotation(ExcelName.class);
                    String annName = myFieldAnn.name();
                    if (StringUtils.hasLength(annName)) {
                        tableTitleName = annName;
                    }
                    tableNames.append(tableTitleName).append(CSV_DELIMITER);
                }
            } catch (Exception e) {
                log.warn("buildCsvFileTableNames() Exception={}", e.getMessage());
            }
        }
        return tableNames.append(CSV_TAIL).toString();
    }
    
    /**
     * 构建excel内容
     *
     * @param dataLists
     * @return
     */
    public static String buildCsvFileBodyMap(List<?> dataLists) {
        //不管你传什么玩意，我都给你反射一手，搞成Map
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Object o : dataLists) {
            mapList.add(toMap(o));
        }
        //然后利用csv格式，对着map嘎嘎一顿拼接数据
        StringBuilder lineBuilder = new StringBuilder();
        for (Map<String, Object> rowData : mapList) {
            for (String key : rowData.keySet()) {
                Object value = rowData.get(key);
                if (Objects.nonNull(value)) {
                    lineBuilder.append(value).append(CSV_DELIMITER);
                } else {
                    lineBuilder.append("--").append(CSV_DELIMITER);
                }
            }
            lineBuilder.append(CSV_TAIL);
        }
        return lineBuilder.toString();
    }
    
    /**
     * 类转map
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> toMap(T entity) {
        Class<? extends Object> bean = entity.getClass();
        Field[] fields = bean.getDeclaredFields();
        Map<String, Object> map = new HashMap<>(fields.length);
        for (Field field : fields) {
            try {
                if (!"serialVersionUID".equals(field.getName())) {
                    String methodName =
                            "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    Method method = bean.getDeclaredMethod(methodName);
                    Object fieldValue = method.invoke(entity);
                    map.put(field.getName(), fieldValue);
                }
            } catch (Exception e) {
                log.warn("toMap() Exception={}", e.getMessage());
            }
        }
        return map;
    }
}
