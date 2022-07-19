package com.zsh.cloud.common.web.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.google.common.collect.Sets;
import com.zsh.cloud.common.web.excel.imports.ColumnHeader;
import jodd.io.FileUtil;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Excel 工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/19 10:44
 */
@Slf4j
@UtilityClass
public class ExcelUtils {
    
    /**
     * 将列表以 Excel 响应给前端
     *
     * @param filename  文件名
     * @param sheetName Excel sheet 名
     * @param head      Excel head 头
     * @param data      数据列表哦
     * @param <T>       泛型，保证 head 和 data 类型的一致性
     * @throws IOException 写入失败的情况
     */
    public static <T> void write(String filename, String sheetName, Class<T> head, List<T> data) throws IOException {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse response = Objects.requireNonNull(sra).getResponse();
        // 输出 Excel
        EasyExcel.write(Objects.requireNonNull(response).getOutputStream(), head)
                // 不要自动关闭，交给 Servlet 自己处理
                .autoCloseStream(false)
                // 基于 column 长度，自动适配。最大 255 宽度
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                // sheet 名称
                .sheet(sheetName)
                // 写数据
                .doWrite(data);
        // 设置 header 和 contentType。写在最后的原因是，避免报错时，响应 contentType 已经被修改了
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
    }
    
    /**
     * 导入模板下载.
     *
     * @param fileName       文件名
     * @param clazz
     * @param excludeColumns 排查字段集合
     */
    public static void renderImportFile(String fileName, Class clazz, String... excludeColumns) {
        File file = createImportFile(clazz, excludeColumns);
        if (file != null) {
            ResponseUtils.flushFile(file, fileName, ResponseUtils.MimeEnum.XLSX);
            try {
                FileUtil.delete(file);
            } catch (IOException e) {
                log.warn("临时文件{}删除失败，原因{}", file.getAbsolutePath(), e);
            }
        }
    }
    
    /**
     * 创建excel文件.
     *
     * @param clazz
     * @param excludeColumns
     * @return
     */
    @SneakyThrows
    public static File createImportFile(Class clazz, String... excludeColumns) {
        Set<String> excludeSet = Sets.newHashSet(excludeColumns);
        File file = FileUtil.createTempFile();
        try (FileOutputStream stream = FileUtils.openOutputStream(file);) {
            //获取所有属性
            java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
            List<ColumnHeader> columnHeaders = new ArrayList<>();
            for (java.lang.reflect.Field field : fields) {
                if (field.isAnnotationPresent(ColumnHeader.class) && !excludeSet.contains(field.getName())) {
                    ColumnHeader columnHeader = field.getAnnotation(ColumnHeader.class);
                    columnHeaders.add(columnHeader);
                }
            }
            if (columnHeaders.isEmpty()) {
                return null;
            }
            //创建work
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFFont font = workbook.getFontAt(0);
            font.setCharSet(HSSFFont.DEFAULT_CHARSET);
            //更改默认字体大小
            font.setFontHeightInPoints((short) 10);
            font.setFontName("微软雅黑");
            //创建工作表sheet
            XSSFSheet dataSheet = workbook.createSheet("数据区");
            //创建导入列说明
            XSSFSheet noteSheet = workbook.createSheet("列定义说明");
            
            //插入第一行数据的表头
            //数据区
            assignmentDataSheet(columnHeaders, workbook, dataSheet);
            //列定义说明
            columnDescription(columnHeaders, noteSheet);
            //将excel写入
            workbook.write(stream);
            return file;
        } catch (IOException e) {
            log.error("error", e);
        }
        return null;
    }
    
    /**
     * 示例.
     *
     * @param columnHeaders
     * @param workbook
     * @param dataSheet
     */
    private static void assignmentDataSheet(List<ColumnHeader> columnHeaders, XSSFWorkbook workbook,
            XSSFSheet dataSheet) {
        //创建行, 第一行是标题
        XSSFRow row = dataSheet.createRow(0);
        XSSFRow row1 = dataSheet.createRow(1);
        XSSFCell cell;
        XSSFCell cell1;
        for (int i = 0; i < columnHeaders.size(); i++) {
            //标题
            cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(columnHeaders.get(i).title());
            if (columnHeaders.get(i).notNull()) {
                // 设置字体
                XSSFFont redFont = workbook.createFont();
                redFont.setColor(Font.COLOR_RED);
                redFont.setColor(Font.COLOR_RED);
                redFont.setCharSet(HSSFFont.DEFAULT_CHARSET);
                //更改默认字体大小
                redFont.setFontHeightInPoints((short) 10);
                redFont.setFontName("微软雅黑");
                CellStyle redStyle = workbook.createCellStyle();
                redStyle.setFont(redFont);
                cell.setCellStyle(redStyle);
            }
            //示例值
            cell1 = row1.createCell(i);
            cell1.setCellType(CellType.STRING);
            cell1.setCellValue(columnHeaders.get(i).example());
        }
    }
    
    /**
     * 列定义说明.
     *
     * @param columnHeaders
     * @param noteSheet
     */
    private static void columnDescription(List<ColumnHeader> columnHeaders, XSSFSheet noteSheet) {
        //插入第一行数据的表头
        //定义表头
        //创建行, 第一行是标题
        XSSFRow row = noteSheet.createRow(0);
        String[] title = {"列名", "是否必填", "示例值", "描述"};
        XSSFCell cell;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(title[i]);
        }
        //插入数据
        XSSFRow dataRow;
        for (int i = 0; i < columnHeaders.size(); i++) {
            dataRow = noteSheet.createRow(i + 1);
            //列名
            XSSFCell cell0 = dataRow.createCell(0);
            cell0.setCellType(CellType.STRING);
            cell0.setCellValue(columnHeaders.get(i).title());
            //是否必填
            XSSFCell cell1 = dataRow.createCell(1);
            cell1.setCellType(CellType.STRING);
            cell1.setCellValue(columnHeaders.get(i).notNull() ? "是" : "否");
            //示例值
            XSSFCell cell2 = dataRow.createCell(2);
            cell2.setCellType(CellType.STRING);
            cell2.setCellValue(columnHeaders.get(i).example());
            //描述
            XSSFCell cell3 = dataRow.createCell(3);
            cell3.setCellType(CellType.STRING);
            cell3.setCellValue(columnHeaders.get(i).desc());
        }
    }
}
