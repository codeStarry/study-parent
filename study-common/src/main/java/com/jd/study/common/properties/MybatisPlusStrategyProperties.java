package com.jd.study.common.properties;

import lombok.Data;

/**
 * mybatis-plus策略配置
 * @author lsy
 * @date 2020-07-18
 */
@Data
public class MybatisPlusStrategyProperties {

    /**
     * 表名转实体名规则，是否下划线转驼峰
     */
    private Boolean naming = true;

    /**
     *表字段名转属性规则，是否下划线转驼峰
     */
    private Boolean columnNaming = true;

    /**
     *实体类中属性与表字段映射是否全部写明注释
     */
    private Boolean entityTableFieldAnnotationEnable = true;

    /**
     *实体是否启用Lombok模型注解
     */
    private Boolean entityLombokModel = false;

    /**
     *实体是否启用Builder构建器模型注解
     */
    private Boolean entityBuilderModel = false;

    /**
     * 乐观锁字段
     */
    private String versionFieldName;

    /**
     * 逻辑删字段
     */
    private String logicDeleteFieldName;
}
