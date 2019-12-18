### 使用方法
* 生成archetype
* * 确保本地maven正确安装，能使用 mvn 命令
* * 进入blade-single-archetype 文件夹，在pom文件那一层
* * 运行 mvn archetype:create-from-project
* * cd target/generated-sources/archetype 到这个目录
* * 运行 mvn clean install
* 使用archetype创建项目
* * 在一个空文件夹上面运行 mvn archetype:generate -DarchetypeCatalog=local local代表使用本地archetype
* * 按照提示，一个一个输入

已经在 maven 3.5.2 测试通过。jdk是1.8