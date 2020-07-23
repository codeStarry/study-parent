package com.jd.study.common.third;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.jd.study.common.properties.CustomizeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * OSS工具类
 * @author lsy
 * @date 2020-7-16
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OssThird {

    private final CustomizeProperties customizeProperties;

    private static CustomizeProperties properties;

    private static OSS client;

    /**
     * 加载OSS配置
     */
    @PostConstruct
    private void init() {
        OssThird.properties = this.customizeProperties;
    }

    /**
     * 初始化OSS客户端
     */
    private static void initOss() {
        client = new OSSClientBuilder().build(properties.getOss().getEndpoint(), properties.getOss().getAccessKeyId(), properties.getOss().getSecretAccessKey());
    }

    /**
     * 关闭OSS连接
     */
    private static void shutdown() {
        client.shutdown();
    }

    /**
     * 通用文件上传
     * @param localPath   本地地址
     * @return  文件url
     * @throws IOException
     */
    public static String uploadFile(String localPath) throws IOException{
        File file = new File(localPath);
        String filePath = singleUpload(file);
        try {
            initOss();

            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            URL url = client.generatePresignedUrl(properties.getOss().getBucketName(), filePath, expiration);
            String uri = url.toString();
            uri = uri.substring(0, uri.indexOf("?"));

            return uri;
        } finally {
            shutdown();
        }

    }

    /**
     * 删除文件
     * @param list
     * @return
     * @throws Exception
     */
    public static List<String> delFiles(List<String> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        List<String> keys = new ArrayList<>(list.size());
        list.forEach(i -> {
            String str  = i.substring(i.indexOf("/", 10) + 1, i.length());
            keys.add(str);
        });
        DeleteObjectsResult result = client.deleteObjects(new DeleteObjectsRequest(properties.getOss().getBucketName()).withKeys(keys));

        return result.getDeletedObjects();
    }

    /**
     * 删除文件
     * @param url
     * @throws Exception
     */
    public static void delFile(String url) throws Exception {
        try {
            initOss();

            client.deleteObject(properties.getOss().getBucketName(), url);

        } finally {
            shutdown();
        }
    }

    /**
     * 通用文件上传模板
     * @param file
     * @return        文件路径
     */
    public static String singleUpload(File file) throws IOException {
        initOss();
        try(InputStream stream = new FileInputStream(file)) {

            String fileName = getFileName(file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(stream.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");

            client.putObject(properties.getOss().getBucketName(), properties.getOss().getFileDir() + fileName, stream, metadata);

            return properties.getOss().getFileDir() + fileName;
        }finally {
            shutdown();
        }
    }

    /**
     * 获取文件名
     * @param file
     * @return
     */
    private static String getFileName(File file) {
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        fileName = random.nextInt(10000) + System.currentTimeMillis() + suffix;
        return fileName;
    }
}
