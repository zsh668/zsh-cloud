package com.zsh.cloud.common.oss.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.zsh.cloud.common.oss.service.OssTemplate;
import com.zsh.cloud.common.oss.service.impl.OssTemplateImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * oss配置bean.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/1 14:09
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public AmazonS3 ossClient(OssProperties ossProperties) {
        // 客户端配置，主要是全局的配置信息
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxConnections(ossProperties.getMaxConnections());
        // url以及region配置
        AwsClientBuilder.EndpointConfiguration endpointConfiguration =
            new AwsClientBuilder.EndpointConfiguration(ossProperties.getEndpoint(), ossProperties.getRegion());
        // 凭证配置
        AWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(), ossProperties.getSecretKey());
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        // build amazonS3Client客户端
        return AmazonS3Client.builder().withEndpointConfiguration(endpointConfiguration).withClientConfiguration(clientConfiguration)
            .withCredentials(awsCredentialsProvider).disableChunkedEncoding().withPathStyleAccessEnabled(ossProperties.getPathStyleAccess()).build();
    }
    
    @Bean
    @ConditionalOnBean(AmazonS3.class)
    public OssTemplate ossTemplate(AmazonS3 amazonS3) {
        return new OssTemplateImpl(amazonS3);
    }
}
