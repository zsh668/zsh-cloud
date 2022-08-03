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
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/zsh_cloud", "root", "root").globalConfig(builder -> {
                    builder.author("zhangshuhang") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("./tmp"); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent("com.zsh.cloud") // 设置父包名
                            .moduleName("sms") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "./tmp/com/zsh/cloud/mapper/xml")); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.addInclude("sms_black_list", "sms_config", "sms_config_signature", "sms_config_template",
                                    "sms_mail_group", "sms_mail_list", "sms_mail_list_group", "sms_manual_process", "sms_marketing",
                                    "sms_platform", "sms_receive_log", "sms_send_log", "sms_sensitive_list", "sms_signature",
                                    "sms_template", "sms_timing_push") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
