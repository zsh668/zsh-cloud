package com.zsh.cloud.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 代码生成.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 14:57
 */
public class Gen {
    
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/zsh_cloud", "root", "root").globalConfig(builder -> {
                    builder.author("zhangshuhang") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("./tmp"); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent("com.zsh.cloud") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "./tmp")); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.addInclude("cw_order_extends") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
