package com.jd.study.common.properties;

import lombok.Data;

/**
 * mybatis-plus全局配置
 * @author lsy
 * @date 2020-07-18
 */
@Data
public class MybatisPlusGlobalProperties {

    /**
     * 生成文件位于项目里的相对路径
     */
    private String outputDir;

    /**
     *是否覆盖已有文件
     */
    private Boolean fileOverride = false;

    /**
     * 是否启用swagger注解
     */
    private Boolean swagger = false;

    /**
     * 作者
     */
    private String author;

    /**
     * 数据库类型
     */
    private String dbType;
}
