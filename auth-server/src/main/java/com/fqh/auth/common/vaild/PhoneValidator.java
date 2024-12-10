package com.fqh.auth.common.vaild;

import com.fqh.auth.config.handle.ServiceException;
import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneValidator {

    private static int MAX_PHONE_LENGTH = 50;

    private static final String PHONE_REGEX = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    private final Pattern pattern;

    public PhoneValidator() {
        pattern = Pattern.compile(PHONE_REGEX);
    }

    /**
     * 检查电话
     *
     * @param phone 电话
     */
    public void checkPhone(String phone) {
        if (Strings.isNullOrEmpty(phone)) {
            throw new ServiceException("The phone cannot be null or empty");
        }

        // check max phone length
        if (phone.length() > MAX_PHONE_LENGTH) {
            throw new ServiceException(String.format("The phone is too long: max number of chars is %s",
                    MAX_PHONE_LENGTH));
        }

        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new ServiceException(String.format("The phone provided %s is not formal valid", phone));
        }
    }

}
