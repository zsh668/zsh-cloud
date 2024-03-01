package com.zsh.cloud.common.oss.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.zsh.cloud.common.oss.service.OssTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * OssTemplate的实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/1 14:05
 */
@RequiredArgsConstructor
public class OssTemplateImpl implements OssTemplate {
    
    private final AmazonS3 amazonS3;
    
    @Override
    public void createBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket((bucketName));
        }
    }
    
    @Override
    public List<Bucket> getAllBuckets() {
        return amazonS3.listBuckets();
    }
    
    @Override
    public void removeBucket(String bucketName) {
        amazonS3.deleteBucket(bucketName);
    }
    
    @Override
    public void putObject(String bucketName, String objectName, InputStream stream, String contextType) throws Exception {
        putObject(bucketName, objectName, stream, stream.available(), contextType);
    }
    
    @Override
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }
    
    /**
     * @param bucketName
     * @param objectName
     * @param stream
     * @param size
     * @param contextType
     * @return
     */
    @SneakyThrows
    private PutObjectResult putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) {
        byte[] bytes = IOUtils.toByteArray(stream);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contextType);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        // 上传
        return amazonS3.putObject(bucketName, objectName, byteArrayInputStream, objectMetadata);
    }
    
    @Override
    public S3Object getObject(String bucketName, String objectName) {
        return amazonS3.getObject(bucketName, objectName);
    }
    
    @Override
    public String getObjectUrl(String bucketName, String objectName, Integer expires) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, expires);
        URL url = amazonS3.generatePresignedUrl(bucketName, objectName, calendar.getTime());
        return url.toString();
    }
    
    @Override
    public void removeObject(String bucketName, String objectName) throws Exception {
        amazonS3.deleteObject(bucketName, objectName);
    }
    
    @Override
    public List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        ObjectListing objectListing = amazonS3.listObjects(bucketName, prefix);
        return objectListing.getObjectSummaries();
    }
    
}
