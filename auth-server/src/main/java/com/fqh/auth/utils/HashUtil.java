package com.fqh.auth.utils;

import com.fqh.auth.config.handle.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashUtil {

    // 生成随机盐
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // 哈希密码
    public static String hashPassword(String password, String salt){
        String saltedPassword = salt + password;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("获取哈希实例失败！");
        }
        byte[] hashedPassword = md.digest(saltedPassword.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
