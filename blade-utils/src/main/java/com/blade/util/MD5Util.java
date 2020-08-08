package com.blade.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * MD5 工具
 *
 * @author Blade
 */
public class MD5Util {
    private static final Logger LOG = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private MD5Util() {
    }

    private static final String[] HEX = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 32位MD5签名值
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encode32(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            return byteArrayToHexString(byteArray);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return str;
    }

    /**
     * 32位大写MD5签名值
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encode32ToUpperCase(String str) {
        return encode32(str).toUpperCase();
    }

    /**
     * 16位MD5签名值
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encode16(String str) {
        return encode32(str).substring(8, 24);
    }

    /**
     * 16位大写MD5签名值
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encode16ToUpperCase(String str) {
        return encode32ToUpperCase(str).substring(8, 24);
    }

    public static String encode(String str, String enc) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = md5.digest(str.getBytes(enc));
            return byteArrayToHexString(byteArray);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return str;
    }

    /**
     * byte数组转成hex字符串
     *
     * @param byteArray byte数组
     * @return hex字符串
     */
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (byte b : byteArray) {
            sb.append(byteToHexChar(b));
        }
        return sb.toString();
    }

    /**
     * byte 转 hex
     *
     * @param b byte
     * @return hex
     */
    private static Object byteToHexChar(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX[d1] + HEX[d2];
    }

    public static void main(String[] args) {
        String ss = "test";
        System.out.println(MD5Util.encode32(ss));
        System.out.println(MD5Util.encode32ToUpperCase(ss));
        System.out.println(MD5Util.encode16(ss));
        System.out.println(MD5Util.encode16ToUpperCase(ss));
    }
}
