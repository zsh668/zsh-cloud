package com.zsh.cloud.common.influxdb;

import com.zsh.cloud.common.influxdb.core.InfluxdbTemplate;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * influxdb自动配置类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 13:47
 */
@Configuration
@EnableConfigurationProperties({InfluxdbProperties.class})
@ConditionalOnProperty(prefix = "zsh.cloud.influxdb", value = "enable", matchIfMissing = true)
public class InfluxdbAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public InfluxDB influxdb(InfluxdbProperties influxdbProperties) {
        InfluxDB influxDB = InfluxDBFactory.connect(influxdbProperties.getUrl(), influxdbProperties.getUsername(), influxdbProperties.getPassword());
        influxDB.setDatabase(influxdbProperties.getDatabase());
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }
    
    @Bean
    public InfluxdbTemplate influxdbTemplate(InfluxdbProperties influxdbProperties) {
        return new InfluxdbTemplate(influxdbProperties);
    }
}
