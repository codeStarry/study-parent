package com.jd.study.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置类
 * @author lsy
 * @date 2020-7-16
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system.customize", ignoreUnknownFields = true)
public class CustomizeProperties {

    /**
     * OSS配置
     */
    @NestedConfigurationProperty
    private final OssProperties oss = new OssProperties();

    /**
     * mybatis-plus生成规则配置
     */
    @NestedConfigurationProperty
    private final MybatisPlusGeneratorProperties mybatisPlusGenerator = new MybatisPlusGeneratorProperties();

}
