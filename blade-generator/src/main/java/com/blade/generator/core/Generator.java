package com.blade.generator.core;

import com.blade.generator.core.generators.EntityGenerator;
import com.blade.generator.core.generators.IGenerator;
import com.blade.generator.core.generators.MapperGenerator;
import com.blade.generator.core.generators.ServiceGenerator;

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
        packageConfig.setSavePath("F:\\");
        generatorInfo.setPackageConfig(packageConfig);

        GenerateFileConfig generateFileConfig = new GenerateFileConfig();
        generatorInfo.setGenerateFileConfig(generateFileConfig);

        List<String> tables = new ArrayList<>();
        tables.add("dept");
//        tables.add("dict");
//        tables.add("dict_detail");
//        tables.add("menu");
//        tables.add("job");
//        tables.add("permission");
//        tables.add("role");
//        tables.add("roles_depts");
//        tables.add("roles_menus");
//        tables.add("roles_permissions");
//        tables.add("users_roles");
        generatorInfo.setGenerateTables(tables);

        List<TableInfo> tableInfoList = generatorInfo.getTableFromDb();
        tableInfoList.forEach(tableInfo -> {
            IGenerator entityGenerator = new EntityGenerator(tableInfo, generatorInfo);
            IGenerator mapperGenerator = new MapperGenerator(entityGenerator, tableInfo, generatorInfo);
            IGenerator serviceGenerator = new ServiceGenerator(mapperGenerator, tableInfo, generatorInfo);
//            IGenerator controllerGenerator = new ControllerGenerator(serviceGenerator, tableInfo, generatorInfo);
            serviceGenerator.generate();
        });
    }
}