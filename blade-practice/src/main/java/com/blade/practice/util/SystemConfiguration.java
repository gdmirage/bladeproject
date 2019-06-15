package com.blade.practice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * TODO:
 * 获取系统指定properties文件的参数
 *
 * @author Blade
 * @date 2019/4/14 21:44
 */
public class SystemConfiguration {

    private String configPath = null;

    private Properties properties = null;

    private SystemConfiguration(){}

    public SystemConfiguration(String configPath) {
        this.configPath = configPath;
        init();
    }

    private void init() {
        properties = new Properties();
        InputStream inputStream = PropertiesUtils.class.getResourceAsStream(this.configPath);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("获取配置文件异常");
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
