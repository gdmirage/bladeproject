package com.blade.core.serializer;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author blade
 * 2019/11/19 14:47
 */
public class JdkSerializer {

    public static byte[] serialize(Object object) throws Exception {
        if (null == object) {
            return null;
        }

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            throw e;
        }
    }

    public static Object deserialize(byte[] bytes) throws Exception {
        if (null == bytes) {
            return null;
        }

        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;

        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.put("a" + i, "b" + i);
        }

        System.out.println(RamUsageEstimator.sizeOf(map));
        byte[] bytes = JdkSerializer.serialize(map);
        System.out.println(RamUsageEstimator.sizeOf(bytes));
        Object o = JdkSerializer.deserialize(bytes);
        System.out.println(RamUsageEstimator.sizeOf(o));
    }
}
