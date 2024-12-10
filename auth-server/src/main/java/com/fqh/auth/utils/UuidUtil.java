package com.fqh.auth.utils;


import java.util.UUID;

public class UuidUtil {

    private static String getUUid() {
        String uuidStr = UUID.randomUUID().toString().replaceAll("-", ",");
        return uuidStr.substring(20);
    }

}
