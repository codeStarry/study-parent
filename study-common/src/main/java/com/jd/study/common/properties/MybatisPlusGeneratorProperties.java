package com.jd.study.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * mybatis-plus代码生成配置
 * @author lsy
 * @date 2020-07-18
 */
@Data
public class MybatisPlusGeneratorProperties {

    //------------------包名配置---------------------

    /**
     * 上级包位置
     */
    private String packageParent;

    /**
     * 生成的Entity所处包
     */
    private String packageEntity = "model";

    /**
     *生成的Mapper所处包
     */
    private String packageDao = "mapper";

    /**
     *生成的Service所处包
     */
    private String packageService = "service";

    /**
     *生成的ServiceImpl所处包
     */
    private String packageServiceImpl = "service.impl";

    /**
     *生成的Controller所处包
     */
    private String packageController = "controller";

    /**
     *生成的Mapper.xml所处资源文件夹
     */
    private String xmlDirectoryName = "/mapper";

    //-----------------模板配置---------------------

    /**
     * 生成的mapping文件夹位于项目里的路径
     */
    private String templateMappingDir = "/study-repo/src/main/resources";

    /**
     * xml模板路径
     */
    private String templateXmlVmDir = "/templates/mapper.xml";

    /**
     * entity模板路径
     */
    private String templateEntityVmDir = "/templates/entity.java";

    /**
     * mapper模板路径
     */
    private String templateDaoVmDir = "/templates/mapper.java";

    /**
     * service模板路径
     */
    private String templateServiceVmDir = "/templates/service.java";

    /**
     * serviceImpl模板路径
     */
    private String templateServiceImplVmDir = "/templates/serviceImpl.java";

    /**
     * controller模板路径
     */
    private String templateControllerVmDir = "/templates/controller.java";

    //------------------全局配置子-----------------

    @NestedConfigurationProperty
    private final MybatisPlusGlobalProperties global = new MybatisPlusGlobalProperties();


    //-----------------策略配置--------------------

    @NestedConfigurationProperty
    private final MybatisPlusStrategyProperties strategy = new MybatisPlusStrategyProperties();
}
