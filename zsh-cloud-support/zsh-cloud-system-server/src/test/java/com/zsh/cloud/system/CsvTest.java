package com.zsh.cloud.system;

import com.zsh.cloud.common.web.excel.export.csv.CsvFileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshuhang
 * @version 1.0
 * @date 2023/12/29 15:28
 */
public class CsvTest {
    
    public static void main(String[] args) throws IOException {
        //类不确定 随便怎么传都行
        List<UserVo> districts = new ArrayList<>();
        districts.add(new UserVo().setAccount("11").setUserName("12"));
        districts.add(new UserVo().setAccount("21").setUserName("22"));
        districts.add(new UserVo().setAccount("31").setUserName("32"));
        //存放地址&文件名
        String fileName = "/Users/zhangshuhang/Downloads/" + CsvFileUtil.buildCsvFileFileName("用户");
        //写入数据
        String content = CsvFileUtil.buildCsvFile(districts);
        //调用方法生成
        CsvFileUtil.writeFile(fileName, content);
        //写入数据
        String contentBody = CsvFileUtil.buildCsvFileBodyMap(districts);
        //调用方法生成
        CsvFileUtil.writeFile(fileName, contentBody);
    }
}
