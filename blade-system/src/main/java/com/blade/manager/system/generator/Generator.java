package com.blade.manager.system.generator;

import com.blade.generator.core.GeneratorInfo;
import com.blade.generator.core.NamingStrategy;
import com.blade.generator.core.TableInfo;
import com.blade.generator.core.config.AbstractDbConfig;
import com.blade.generator.core.config.GenerateFileConfig;
import com.blade.generator.core.config.GlobalConfig;
import com.blade.generator.core.config.MysqlDbConfig;
import com.blade.generator.core.config.PackageConfig;
import com.blade.generator.core.generators.ControllerGenerator;
import com.blade.generator.core.generators.EntityGenerator;
import com.blade.generator.core.generators.IGenerator;
import com.blade.generator.core.generators.MapperGenerator;
import com.blade.generator.core.generators.PageSearchDtoGenerator;
import com.blade.generator.core.generators.ServiceGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 *
 * @author blade
 * 2019/12/18 16:54
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
        dbConfig.setDbUrl("jdbc:mysql://localhost:3306/blade_system?useUnicode=true&characterEncoding=utf-8");
        dbConfig.setUser("root");
        dbConfig.setPassword("root");
        generatorInfo.setDbConfig(dbConfig);

        // 命名配置
        generatorInfo.setNamingStrategy(NamingStrategy.underline_2_camel);

        PackageConfig packageConfig = new PackageConfig();
        // 包名
        packageConfig.setPackagePath("com.blade.manager.system");
        // 模块名
        packageConfig.setModule("basis");

        String savePath = System.getProperty("user.dir") + File.separator + "blade-system"
                + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        System.out.println(savePath);
        packageConfig.setSavePath(savePath);
        generatorInfo.setPackageConfig(packageConfig);

        GenerateFileConfig generateFileConfig = new GenerateFileConfig();
        generatorInfo.setGenerateFileConfig(generateFileConfig);

        // 生成的表配置
        List<String> tables = new ArrayList<>();
        tables.add("dict");
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

        // entity 忽略生成的字段, 一般是 BaseEntity 里面的字段
        String entityIgnoreColumn = "id,creator,create_time,modifier,modify_time,is_delete";
        generatorInfo.setEntityIgnoreColumn(entityIgnoreColumn);

        List<TableInfo> tableInfoList = generatorInfo.getTableFromDb();
        tableInfoList.forEach(tableInfo -> {
            IGenerator entityGenerator = new EntityGenerator(tableInfo, generatorInfo);
            IGenerator mapperGenerator = new MapperGenerator(entityGenerator, tableInfo, generatorInfo);
            IGenerator serviceGenerator = new ServiceGenerator(mapperGenerator, tableInfo, generatorInfo);
            IGenerator pageSearchDtoGenerator = new PageSearchDtoGenerator(serviceGenerator, tableInfo, generatorInfo);
            IGenerator controllerGenerator = new ControllerGenerator(pageSearchDtoGenerator, tableInfo, generatorInfo);
            controllerGenerator.generate();
        });
    }
}
