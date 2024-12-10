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
        System.out.println(encrypt("sss","sss"));
        System.out.println(decrypt("FjBAsf42Sj9oxzq1yQ9nfA==","sss"));

        System.out.println(decrypt("jjVz5RkxRj342uPW+MwGCuUkflOWfi0f","store2022"));
    }
}
