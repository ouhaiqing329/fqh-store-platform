package com.fqh.auth.common.vaild;

import com.fqh.auth.config.handle.ServiceException;
import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    private static final int MAX_PASSWORD_LENGTH = 60;

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";

    private final Pattern pattern;

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_REGEX);
    }

    /**
     * 检查密码
     *
     * @param password 密码
     */
    public void checkPassword(String password) {
        if (Strings.isNullOrEmpty(password)) {
            throw new ServiceException("Password cannot be null or empty");
        }

        // check max length
        if (password.length() > MAX_PASSWORD_LENGTH) {
            throw new ServiceException(String.format("Password is too long: max number of chars is %s",
                    MAX_PASSWORD_LENGTH));
        }

        // Password must to be at least 8 chars, 1 number, 1 upper case, 1 lower case letter, 1 special char, no spaces
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new ServiceException("Password must to be at least 8 chars, 1 number, 1 upper case," +
                    " 1 lower case letter, 1 special char, no spaces");
        }
    }

}
