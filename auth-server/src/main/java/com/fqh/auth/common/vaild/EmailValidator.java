package com.fqh.auth.common.vaild;

import com.fqh.auth.config.handle.ServiceException;
import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final int MAX_EMAIL_LENGTH = 255;

    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private final Pattern pattern;

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    /**
     * 检查电子邮件
     *
     * @param email 电子邮件
     */
    public void checkEmail(String email) {
        if (Strings.isNullOrEmpty(email)) {
            throw new ServiceException("The Email cannot be null or empty");
        }

        // 检查最大邮件长度
        if (email.length() > MAX_EMAIL_LENGTH) {
            throw new ServiceException(String.format("The Email is too long: max number of chars is %s",
                    MAX_EMAIL_LENGTH));
        }

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ServiceException(String.format("The Email provided %s is not formal valid", email));
        }
    }

}
