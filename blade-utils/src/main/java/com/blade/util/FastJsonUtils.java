package com.blade.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * TODO:
 * FastJson的工具类，主要是为了规范打印的日志
 *
 * @author blade
 * 2020-02-02 16:47
 */
public class FastJsonUtils {

    public static String toJsonString(Object o) {
        return JSONObject.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect);
    }
}
