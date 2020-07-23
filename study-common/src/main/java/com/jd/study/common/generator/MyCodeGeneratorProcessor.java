package com.jd.study.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.jd.study.common.generator.convert.MyConvert;
import com.jd.study.common.properties.CustomizeProperties;
import com.jd.study.common.properties.MybatisPlusGlobalProperties;
import com.jd.study.common.properties.MybatisPlusStrategyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * sql生成器
 * @author lsy
 * @date 2020-07-18
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MyCodeGeneratorProcessor {

    private final DataSourceProperties dataProperties;

    private final CustomizeProperties properties;

    //项目当前路径
    private static String projectPath = System.getProperty("user.dir");

    public void codeGenerator() {
        if (null == properties || null == dataProperties) {
            System.err.println("获取到配置文件中的配置项为空!");
            return;
        }
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //------------------全局配置--------------------------------

        MybatisPlusGlobalProperties global = properties.getMybatisPlusGenerator().getGlobal();
        if (null != global) {
            GlobalConfig gc = new GlobalConfig();
            boolean isNull = null == global.getOutputDir();

            if (isNull) {
                System.err.println("请设置生成文件位于项目里的相对路径！");
            } else {
                gc.setOutputDir(projectPath + global.getOutputDir());
            }
            gc.setFileOverride(global.getFileOverride());
            gc.setSwagger2(global.getSwagger());
            gc.setAuthor(global.getAuthor())
                    .setOpen(false)
                    .setEnableCache(false)
                    .setBaseResultMap(true)
                    .setBaseColumnList(true);
            gc.setActiveRecord(true);
            gc.setControllerName("%sApi");
            mpg.setGlobalConfig(gc);
        }
            //--------------------数据源配置--------------------
            MyConvert myConvert = new MyConvert();
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setDbType(getDbType())
                    .setDriverName(dataProperties.getDriverClassName())
                    .setUrl(dataProperties.getUrl())
                    .setUsername(dataProperties.getUsername())
                    .setPassword(dataProperties.getPassword())
                    .setTypeConvert(myConvert);
            mpg.setDataSource(dsc);

            //-------------------包配置--------------------

            PackageConfig pc = new PackageConfig();

            pc.setParent(properties.getMybatisPlusGenerator().getPackageParent())
                    .setEntity(properties.getMybatisPlusGenerator().getPackageEntity())
                    //这里不进行xml包配置是因为要通过自定义配置把xml优先生成在资源文件夹下
                    .setXml(null)
                    .setMapper(properties.getMybatisPlusGenerator().getPackageDao())
                    .setController(properties.getMybatisPlusGenerator().getPackageController())
                    .setService(properties.getMybatisPlusGenerator().getPackageService())
                    .setServiceImpl(properties.getMybatisPlusGenerator().getPackageServiceImpl());
            mpg.setPackageInfo(pc);

            //-------------------模板配置--------------------

            TemplateConfig templateConfig = new TemplateConfig();
            // 配置自定义输出模板
            //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
            templateConfig.setEntity(properties.getMybatisPlusGenerator().getTemplateEntityVmDir())
                    .setMapper(properties.getMybatisPlusGenerator().getTemplateDaoVmDir())
                    //这里不进行xml模板配置是因为要通过自定义配置把xml优先生成在资源文件夹下
                    .setXml(null)
                    .setController(properties.getMybatisPlusGenerator().getTemplateControllerVmDir())
                    .setService(properties.getMybatisPlusGenerator().getTemplateServiceVmDir())
                    .setServiceImpl(properties.getMybatisPlusGenerator().getTemplateServiceImplVmDir());
            mpg.setTemplate(templateConfig);

            //--------------------策略配置-------------------------
        MybatisPlusStrategyProperties msp = properties.getMybatisPlusGenerator().getStrategy();

        StrategyConfig strategy = new StrategyConfig();
        if (msp.getNaming()) {
            strategy.setNaming(NamingStrategy.underline_to_camel);
        }
        if (msp.getColumnNaming()) {
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        }
        strategy.setEntityTableFieldAnnotationEnable(msp.getEntityTableFieldAnnotationEnable());
        strategy.setEntityLombokModel(msp.getEntityLombokModel());
        strategy.setEntityBuilderModel(msp.getEntityBuilderModel());
        //strategy.setRestControllerStyle(true);
        if (null != msp.getVersionFieldName()) {
            strategy.setVersionFieldName(msp.getVersionFieldName());
        }
        if (null != msp.getLogicDeleteFieldName()) {
            strategy.setLogicDeleteFieldName(msp.getLogicDeleteFieldName());
        }

        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper")
                .setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model")
                .setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService")
                .setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")
                .setInclude(scanner("表名，有多个表英文逗号分割").split(","))
                .setCapitalMode(false);

        mpg.setStrategy(strategy);
        //----------------------------自定义配置----------------------------------

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        //自定义的模板路径，没有自动识别模板类型，模板文件名要指明.vm
        String templatePath = new StringBuilder(properties.getMybatisPlusGenerator().getTemplateXmlVmDir()).append(".vm").toString();
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + properties.getMybatisPlusGenerator().getTemplateMappingDir() + properties.getMybatisPlusGenerator().getXmlDirectoryName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

    /**
     * 读取控制台内容
     * @param tip
     * @return
     */
    public  String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 获取数据库类型
     * @return
     */
    public DbType getDbType() {
        String type = properties.getMybatisPlusGenerator().getGlobal().getDbType();

        switch (type) {
            case "mysql":      return DbType.MYSQL;
            case "mariadb":    return DbType.MARIADB;
            case "oracle":     return DbType.ORACLE;
            case "db2":        return DbType.DB2;
            case "h2":         return DbType.H2;
            case "hsql":       return DbType.HSQL;
            case "sqlite":     return DbType.SQLITE;
            case "postgresql": return DbType.POSTGRE_SQL;
            case "sqlserver2005": return DbType.SQL_SERVER2005;
            case "sqlserver":     return DbType.SQL_SERVER;
            case "dm":            return DbType.DM;
            case "other":         return DbType.OTHER;
        }

        return null;
    }
}
