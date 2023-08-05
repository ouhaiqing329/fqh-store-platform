package com.fqh.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * jasypt工具类
 *
 * @author fqh
 * @date 2023/08/06
 */
public class JasyptUtil {

    /**
     * 加密
     *
     * @param secret   秘钥
     * @param value 值
     * @return {@link String}
     */
    public static String encryptToBase64(String secret,String value){
        //使用PBE模式
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(secret);
        //加密后的密文
        String encrypt = encryptor.encrypt(value);
        //base64编码
        byte[] encode = Base64.getEncoder().encode(encrypt.getBytes(StandardCharsets.UTF_8));
        return new String(encode);
    }

    /**
     * 解密
     *
     * @param secret 秘钥
     * @param value  待解密-密文
     * @return {@link String}
     */
    public static String decryptToBase64(String secret,String value){
        //使用PBE模式
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(secret);
        //解码
        byte[] decode = Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8));
        return encryptor.decrypt(new String(decode));
    }

    /**
     * 加密
     *
     * @param secret   秘钥
     * @param value 值
     * @return {@link String}
     */
    public static String encrypt(String secret,String value){
        //使用PBE模式
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(secret);
        //加密后的密文
        return encryptor.encrypt(value);
    }

    /**
     * 解密
     *
     * @param secret 秘钥
     * @param value  待解密-密文
     * @return {@link String}
     */
    public static String decryptTo(String secret,String value){
        //使用PBE模式
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(secret);
        //解码
        return encryptor.decrypt(value);
    }
}
