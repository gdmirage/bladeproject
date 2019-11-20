package com.blade.core.model.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * 以json字符串的方式重写toString 方法
 *
 * @author blade
 * 2019/11/20 16:27
 */
public class JsonAble implements Serializable{

    private static final long serialVersionUID = 4327605225709127559L;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }
}
