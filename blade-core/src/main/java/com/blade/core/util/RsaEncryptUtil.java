package com.blade.core.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA非对称加密 工具类
 * 同一用base64进行二次加密
 *
 * @author blade
 * 2019/12/2 16:13
 */
public class RsaEncryptUtil {

    /**
     * 非对称加密密钥算法
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 数字签名 签名/验证算法
     */
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * 公钥
     */
    private static final String RSA_PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    private static final String RSA_PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 对外的公钥
     */
    private static final String PUBLIC_KEY = "publicKey";

    /**
     * 对外的私钥
     */
    private static final String PRIVATE_KEY = "privateKey";

    /**
     * RSA密钥长度,默认为1024,密钥长度必须是64的倍数,范围在512~16384位之间
     * 理论上，长度越长，越难破解
     */
    private static final int KEY_SIZE = 512;

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[] 解密数据
     * @throws Exception e
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key)
            throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return byte[] 解密数据
     * @throws Exception e
     */
    public static byte[] decryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        return decryptByPrivateKey(data, getKey(privateKey));
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return byte[] 解密数据
     * @throws Exception e
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key)
            throws Exception {
        //取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成公钥
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data      待解密数据
     * @param publicKey 公钥
     * @return byte[] 解密数据
     * @throws Exception e
     */
    public static byte[] decryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        return decryptByPublicKey(data, getKey(publicKey));
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[] 加密数据
     * @throws Exception e
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key)
            throws Exception {
        //取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        //对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return byte[] 加密数据
     * @throws Exception e
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        return encryptByPublicKey(data, getKey(publicKey));
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[] 加密数据
     * @throws Exception e
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key)
            throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[] 加密数据
     * @throws Exception e
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        return encryptByPrivateKey(data, getKey(key));
    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥Map
     * @return byte[] 私钥
     * @throws Exception e
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(RSA_PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥Map
     * @return byte[] 公钥
     * @throws Exception e
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(RSA_PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 初始化密钥
     *
     * @return 密钥Map
     * @throws Exception e
     */
    private static Map<String, Object> initKey()
            throws Exception {
        //实例化实钥对生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //封装密钥
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(RSA_PUBLIC_KEY, publicKey);
        keyMap.put(RSA_PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte[] 数字签名
     * @throws Exception e
     */
    public static byte[] sign(byte[] data, byte[] privateKey)
            throws Exception {
        //转接私钥材料
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //取私钥对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        //初始化Signature
        signature.initSign(priKey);
        //更新
        signature.update(data);
        //签名
        return signature.sign();
    }

    /**
     * 公钥校验
     *
     * @param data      待校验数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return boolean
     * @throws Exception e
     */
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign)
            throws Exception {
        //转接公钥材料
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        //初始化Signature
        signature.initVerify(pubKey);
        //更新
        signature.update(data);
        //验证
        return signature.verify(sign);
    }

    /**
     * 私钥签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return String 十六进制签名字符串
     * @throws Exception e
     */
    public static String sign(byte[] data, String privateKey)
            throws Exception {
        byte[] sign = sign(data, getKey(privateKey));
        return Hex.encodeHexString(sign);
    }

    /**
     * 公钥校验
     *
     * @param data      待验证数据
     * @param publicKey 公钥
     * @param sign      签名
     * @return boolean 成功返回true,失败返回false
     * @throws Exception e
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        return verify(data, getKey(publicKey), Hex.decodeHex(sign.toCharArray()));
    }

    /**
     * 取得私钥十六进制表示形式
     *
     * @param keyMap 密钥Map
     * @return String 私钥十六进制字符串
     * @throws Exception e
     */
    public static String getPrivateKeyString(Map<String, Object> keyMap)
            throws Exception {
        return Hex.encodeHexString(getPrivateKey(keyMap));
    }

    /**
     * 取得公钥十六进制表示形式
     *
     * @param keyMap 密钥Map
     * @return String 公钥十六进制字符串
     * @throws Exception e
     */
    public static String getPublicKeyString(Map<String, Object> keyMap)
            throws Exception {
        return Hex.encodeHexString(getPublicKey(keyMap));

    }

    /**
     * 获取密钥
     *
     * @param key 密钥
     * @return byte[] 密钥
     * @throws Exception e
     */
    public static byte[] getKey(String key)
            throws Exception {
        return Hex.decodeHex(key.toCharArray());
    }

    /**
     * 用于生成RSA加密用的publicKey 和 privateKey
     * publicKey 和 privateKey  使用了Base64加密
     *
     * @return {@link Map<String,Object>} keyMap
     * @throws Exception 异常
     */
    public static Map<String, Object> generateKey() throws Exception {
        Map<String, Object> keyMap = null;
        keyMap = RsaEncryptUtil.initKey();
        String publicKey = Base64.encodeBase64String((RsaEncryptUtil.getPublicKey(keyMap)));
        String privateKey = Base64.encodeBase64String((RsaEncryptUtil.getPrivateKey(keyMap)));
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> keyMap = generateKey();
        String publicKey = keyMap.get(PUBLIC_KEY).toString();
        String privateKey = keyMap.get(PRIVATE_KEY).toString();

        System.out.println(publicKey);
        System.out.println(privateKey);
//
//        String puKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXzLDeb18UvaAVVBbpmIjXH+zioJNzHixEEIBKbFTwvH0y+2pD3AMsiMoqj3kENg0i+gZajj/usR8K0CCrpgztfjewYgnZjAhUIO5pp3CY1GdZO7XpHSVWLVXJPvEvKFveema5IoesDn/V2ivCEgpCZkJjbJKudB//7Z7ZZDD9qQIDAQAB";
//        String prKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJfMsN5vXxS9oBVUFumYiNcf7OKgk3MeLEQQgEpsVPC8fTL7akPcAyyIyiqPeQQ2DSL6BlqOP+6xHwrQIKumDO1+N7BiCdmMCFQg7mmncJjUZ1k7tekdJVYtVck+8S8oW956Zrkih6wOf9XaK8ISCkJmQmNskq50H//tntlkMP2pAgMBAAECgYAbnm5/yhuulmHN/6K7lbkwCur6wOSTCWZca/QrllJOCr1szTmkZx9ctvaxj7EhBwSU3JDTYRZYw3k2am/HS0sGkahGeBGZyarXGCWQIi5JocAqNjT8PQHnLVl1jwV5jADu999vBcmAWBYXEQT4/UXfQtbHVt68DLMV8vl/c9cAkQJBAPLNQqeKuFXSSKP5KF5nQCZ8MUEXyqfJ5wFCDAkklymxfEMrb59iarAPflto5XmlNtcJz8Z46FpqFntUBo9Qc18CQQCgDRPOwxrJZCUD8WJwfPDwWVZjO7yv9yAuYdMvNRgXhEa3vPxITvIn8L8VC6R4IRrUkOXMRYIvBtZWZODNAnP3AkAc14cocdEN2MnqOIm0FR3ItI4BRns7TT+UpazaVS9js4KoU/uRelZkDaMb5q0Dsz19c9vrnqczPlDnW4bf9VWPAkAQfAhUPWge4f+l/lCjQVsXhjwNkHOItC1OWakErbVDhs450m8xi2AJmRQ1OLPB47b9ucbRWT9lVJDy6QT2WJBVAkBPfK4BVdpLCQVYYvJSxB4ne54nqicG/oymDli/49vKPj97G1n9oV637DXKQIMj2UegSqprdURxibdrnrXaNlCt";

        byte[] puBytes = getKey(publicKey);
        byte[] prBytes = getKey(privateKey);
//
        String data = "root";
//
        byte[] puEcy = encryptByPublicKey(data.getBytes("UTF-8"), puBytes);

        String baPuEcy = Base64.encodeBase64String(puEcy);
        System.out.println(baPuEcy);

        byte[] dcyBytes = Base64.decodeBase64(baPuEcy);
        byte[] prDcy = decryptByPrivateKey(dcyBytes, prBytes);
        System.out.println(new String(prDcy));

//        Map<String, Object> keyMap = initKey();
//        String privateKey = getPrivateKeyString(keyMap);
//        String publicKey = getPublicKeyString(keyMap);
//
//        byte[] a = encryptByPublicKey(data.getBytes(), getPublicKey(keyMap));
//        System.out.println(new String(a));
//
//        byte[] b = decryptByPrivateKey(a, getPrivateKey(keyMap));
//        System.out.println(new String(b));
    }
}
