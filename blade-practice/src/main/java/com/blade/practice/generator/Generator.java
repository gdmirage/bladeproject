package com.blade.practice.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author blade
 * 2019/10/23 16:51
 */
public class Generator {

    public static void main(String[] args) {
        generate();
    }

    private static void generate() {
        GeneratorInfo generatorInfo = new GeneratorInfo();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("Blade");
        generatorInfo.setGlobalConfig(globalConfig);

        // db配置
        AbstractDbConfig dbConfig = new MysqlDbConfig();
        dbConfig.setDbUrl("jdbc:mysql://localhost:3306/manager_system?useUnicode=true&characterEncoding=utf-8");
        dbConfig.setUser("root");
        dbConfig.setPassword("root");
        generatorInfo.setDbConfig(dbConfig);

        // 命名配置
        generatorInfo.setNamingStrategy(NamingStrategy.underline_2_camel);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setPackagePath("com.blade.practice.generator");
        String savePath = System.getProperty("user.dir") + File.separator + "blade-practice"
                + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        packageConfig.setSavePath(savePath);
        generatorInfo.setPackageConfig(packageConfig);

        GenerateFileConfig generateFileConfig = new GenerateFileConfig();
        generatorInfo.setGenerateFileConfig(generateFileConfig);

        List<String> tables = new ArrayList<>();
        tables.add("user");
        generatorInfo.setGenerateTables(tables);

        List<TableInfo> tableInfoList = generatorInfo.getTableFromDb();
        tableInfoList.forEach(tableInfo -> {
            IGenerator entityGenerator = new EntityGenerator(tableInfo, generatorInfo);
//            IGenerator mapperGenerator = new MapperGenerator(entityGenerator, tableInfo, generatorInfo);
//            IGenerator serviceGenerator = new ServiceGenerator(mapperGenerator, tableInfo, generatorInfo);
//            IGenerator controllerGenerator = new ControllerGenerator(serviceGenerator, tableInfo, generatorInfo);
            entityGenerator.generate();
        });
    }
}
