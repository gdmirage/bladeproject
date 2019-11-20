package com.blade.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/9/29 16:09
 */
public class PropertiesUtils {

    private static Properties properties = null;

    private static final String CONFIG_PATH = "/config.properties";

    private PropertiesUtils() {

    }

    public static Properties getInstance() {
        if (null == properties) {
            synchronized (PropertiesUtils.class) {
                if (null == properties) {
                    properties = new Properties();
                    InputStream in = PropertiesUtils.class.getResourceAsStream(CONFIG_PATH);
                    try {
                        properties.load(in);
                    } catch (IOException e) {
                        System.out.println("获取配置文件异常");
                    } finally {
                        if(null != in) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return properties;
    }

    public static String getConfigValue(String key) {
        return getConfigs().get(key);
    }

    private static Map<String, String> getConfigs() {

        Map<String, String> map = new HashMap<>();

        for (Map.Entry<Object, Object> entry : getInstance().entrySet()) {
            map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return map;
    }

}
