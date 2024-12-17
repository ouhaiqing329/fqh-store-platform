package com.fqh.auth.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public class JasyptUtil {

    public static String encrypt(String text, String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword(password);
        encryptor.setConfig(config);
        return encryptor.encrypt(text);
    }

    public static String decrypt(String text, String password) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setPassword(password);
        decryptor.setConfig(config);
        return decryptor.decrypt(text);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("root","fqh@store2024"));
        System.out.println(encrypt("fqh@mysql","fqh@store2024"));
        System.out.println(encrypt("fqh@store2024","fqh@store2024"));
    }
}
