package com.zavarykin.realmentor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ArgumentChecker {

    private static final String LOGIN_REGEX = "^(?!.*[._-]$)(?![._-])[a-zA-Z0-9_.-]{3,20}$";

    private ArgumentChecker() {}

    public static boolean checkUserLogin(String login) {
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

}
