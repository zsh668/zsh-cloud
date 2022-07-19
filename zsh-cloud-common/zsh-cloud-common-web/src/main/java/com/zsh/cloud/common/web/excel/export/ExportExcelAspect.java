package com.zsh.cloud.common.web.excel.export;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.dto.PageQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Excel导出AOP.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/11 11:40
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class ExportExcelAspect {
    
    private static final String IMPORT_KEYWORDS = "export";
    
    /**
     * 触发分批导出阈值
     */
    private static final Long PART_EXPORT_NUM = 5000L;
    
    @Pointcut("@annotation(com.zsh.cloud.common.web.excel.export.ExportExcel)")
    public void pointCut() {
    }
    
    /**
     * 围绕.
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = Objects.requireNonNull(attributes.getResponse());
        if (!isExportRequest(request)) {
            return point.proceed();
        }
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        
        // 获取注解参数值，导出excel
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        ExportExcel exportExcel = methodSignature.getMethod().getAnnotation(ExportExcel.class);
        String fileName = URLEncoder.encode(exportExcel.fileName(), CharEncoding.UTF_8);
        String sheetName = exportExcel.sheetName();
        // 处理response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        Type voType = ((ParameterizedType) (method.getGenericReturnType())).getActualTypeArguments()[0];
        Assert.notNull(voType, method.getName() + "方法返回值需要加泛型才能支持Excel导出，正确示例：Page<SomeVo>");
        
        Assert.notNull(voType, method.getName() + "方法返回值需要加泛型才能支持Excel导出，正确示例：IPage<SomeVo>");
        
        Type rawType = ((ParameterizedType) method.getGenericReturnType()).getRawType();
        if (rawType == Page.class) {
            PageQuery pageQuery = null;
            Object[] args = point.getArgs();
            for (Object arg : args) {
                if (arg instanceof PageQuery) {
                    pageQuery = ((PageQuery) arg);
                    pageQuery.setSize(PART_EXPORT_NUM);
                    pageQuery.setCurrent(1L);
                    break;
                }
            }
            Assert.notNull(pageQuery, method.getName() + "接口返回值为page，入参对象必须继承com.zsh.cloud.common.core.dto.PageQuery");
            ExcelWriter excelWriter = EasyExcelFactory.write(response.getOutputStream(), (Class<?>) voType)
                    .registerWriteHandler(new AdaptiveWidthStyleStrategy()).build();
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(sheetName).build();
            long pages;
            do {
                Object result = point.proceed(args);
                Page<?> page = (Page<?>) result;
                List<?> records = page.getList();
                pages = page.getPages();
                excelWriter.write(records, writeSheet);
                records.clear();
                Objects.requireNonNull(pageQuery).setCurrent(pageQuery.getCurrent() + 1);
            } while (pageQuery.getCurrent() <= pages);
            excelWriter.finish();
        } else if (rawType == List.class) {
            Object result = point.proceed();
            // 写入文件
            EasyExcelFactory.write(response.getOutputStream(), (Class<?>) voType)
                    .registerWriteHandler(new AdaptiveWidthStyleStrategy()).sheet(sheetName).doWrite((List<?>) result);
        } else {
            throw new IllegalArgumentException("返回参数解析错误，导出方法返回值类型只能是List或Page");
        }
        return null;
    }
    
    /**
     * 通过请求中是否含有"export=true"来判断是否为导出请求
     */
    private boolean isExportRequest(HttpServletRequest request) {
        return Boolean.TRUE.toString().equals(request.getParameter(IMPORT_KEYWORDS));
    }
    
    /**
     * 自适应宽度策略 根据每列内容宽度自适应调整，宽度最小不低于10，最大不超过25
     */
    public static class AdaptiveWidthStyleStrategy extends AbstractColumnWidthStyleStrategy {
        
        /**
         * 存储sheet索引：列索引：宽度
         */
        private final Map<Integer, Map<Integer, Integer>> cache = new HashMap<>(2);
        
        @Override
        protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell,
                Head head, Integer integer, Boolean isHead) {
            boolean needSetWidth = isHead || !CollectionUtils.isEmpty(cellDataList);
            if (!needSetWidth) {
                return;
            }
            // 计算列宽
            Integer columnWidth = getColumnWidth(cellDataList, cell, isHead);
            if (columnWidth < 0) {
                return;
            }
            // 最大不超过25，最小不小于10
            columnWidth = Math.min(columnWidth, 25);
            columnWidth = Math.max(columnWidth, 10);
            
            Map<Integer, Integer> maxColumnWidthMap = cache.computeIfAbsent(writeSheetHolder.getSheetNo(),
                    k -> new HashMap<>(16));
            Integer maxColumnWidth = maxColumnWidthMap.get(cell.getColumnIndex());
            if (maxColumnWidth == null || columnWidth > maxColumnWidth) {
                maxColumnWidthMap.put(cell.getColumnIndex(), columnWidth);
                Sheet sheet = writeSheetHolder.getSheet();
                sheet.setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
            }
            
        }
        
        /**
         * 计算宽度
         *
         * @param cellDataList
         * @param cell
         * @param isHead
         * @return
         */
        @SuppressWarnings("all")
        private Integer getColumnWidth(List<WriteCellData<?>> cellDataList, Cell cell, Boolean isHead) {
            if (isHead) {
                // 列头宽度打个折，当这列数据的宽度都很小时，列头文字会换行以节省空间
                return stringWidth(cell.getStringCellValue()) * 8 / 10;
            }
            CellData cellData = cellDataList.get(0);
            CellDataTypeEnum type = cellData.getType();
            if (type == null) {
                return -1;
            }
            switch (type) {
                case STRING:
                    return stringWidth(cellData.getStringValue());
                case BOOLEAN:
                    return cellData.getBooleanValue().toString().getBytes().length;
                case NUMBER:
                    return cellData.getNumberValue().toString().getBytes().length;
                default:
                    return -1;
            }
            
        }
        
        /**
         * 字符串宽度（中文宽度x2）
         */
        private int stringWidth(String str) {
            int chineseLength = (str.getBytes().length - str.length()) / 2;
            int otherLength = str.length() - chineseLength;
            return chineseLength * 2 + otherLength + 1;
        }
    }
}
