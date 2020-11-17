package com.xxqin;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author qinxue
 * @date 2020/11/12 17:50
 */
public class DataBaseCodeGenerator {

    DataBaseCodeGenerator() {
    }

    public static void execute(DataBaseConfig dataBaseConfig, String modelBasePath) {

        System.out.println(dataBaseConfig.toString());
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(modelBasePath + "/src/main/java");
        gc.setAuthor(dataBaseConfig.getAuthor());
        // 会覆盖掉之前生成的文件
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setEntityName("%sEntity");
        gc.setXmlName("%s");
        gc.setDateType(DateType.ONLY_DATE);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataBaseConfig.getConnectionUrl());
        dsc.setDriverName(dataBaseConfig.getDriver());
        dsc.setUsername(dataBaseConfig.getUsername());
        dsc.setPassword(dataBaseConfig.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent(dataBaseConfig.getParentPackage());
        pc.setEntity(dataBaseConfig.getTargetEntityPackage());
        pc.setMapper(dataBaseConfig.getTargetMapperPackage());
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            //自定义属性注入:abc
            //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(16);
                this.setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        if (Objects.nonNull(dataBaseConfig.getTargetMapperXml())) {
            // 如果模板引擎是freemarker
            String templatePath = "templates/mapper_my.xml.ftl";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return modelBasePath
                            + File.separator + "src"
                            + File.separator + "main"
                            + File.separator + "resources"
                            + File.separator + dataBaseConfig.getTargetMapperXml()
                            + File.separator + tableInfo.getMapperName() + StringPool.DOT_XML;
                }
            });
        }

        cfg.setFileOutConfigList(focList);

        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity(dataBaseConfig.getGenEntity()
                ? "templates/entity_my.java" : null);
        templateConfig.setMapper(dataBaseConfig.getGenMapper()
                ? "templates/mapper_my.java" : null);
        // 传入null表示不生成对应的文件
        templateConfig.setService(null);
        templateConfig.setController(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(String.join(",", dataBaseConfig.getTables()).split(","));
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
