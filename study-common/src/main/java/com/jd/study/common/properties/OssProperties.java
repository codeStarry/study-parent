package com.jd.study.common.properties;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * OSS配置类
 * @author lsy
 * @date 2020-7-16
 */
@Data
@Accessors(chain = true)
public class OssProperties {

    /**
     * 地域节点
     */
    private String endpoint;

    /**
     * 账号
     */
    private String accessKeyId;

    /**
     * 密钥
     */
    private String secretAccessKey;

    /**
     * 文件桶名
     */
    private String bucketName;

    /**
     * 文件目录
     */
    private String fileDir;

    /**
     * 自定义文件目录
     */
    @Deprecated
    private Map<String, String> directoryMap = new HashMap<>();
}
