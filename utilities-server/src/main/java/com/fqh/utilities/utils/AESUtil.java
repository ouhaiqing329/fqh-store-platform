package com.fqh.utilities.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

public class AESUtil {


    /**
     * jasypt加密
     *
     * @param value  带加密字符串
     * @param secret 秘钥
     * @return {@link String}
     */
    public static String encrypt(String value, String secret) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword(secret);
        standardPBEStringEncryptor.setConfig(config);
        return standardPBEStringEncryptor.encrypt(value);
    }

    public static String decrypt(String value, String secret) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword(secret);
        standardPBEStringEncryptor.setConfig(config);
        return standardPBEStringEncryptor.decrypt(value);
    }
}
