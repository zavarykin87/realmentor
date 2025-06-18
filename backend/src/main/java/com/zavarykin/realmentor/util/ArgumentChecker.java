package com.zavarykin.realmentor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ArgumentChecker {

    private static final String LOGIN_REGEX = "^(?!.*[._-]$)(?![._-])[a-zA-Z0-9_.-]{3,20}$";

    /**
     * Требования к паролю:
     * 1. запрет на пробелы
     * 2. минимум 1 цифра
     * 3. минимум 1 строчная латинская буква
     * 4. минимум 1 заглавная латинская буква
     * 5. минимум 1 спец символ
     * 6. длина пароля от 8 до 32 символов
     */
    private static final String PASSWORD_REGEX = "^(?=\\S+$)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\\\",.<>/?]).{8,32}$";

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private ArgumentChecker() {}

    public static boolean checkUserLogin(String login) {
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public static boolean checkUserPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean checkUserEmail(String email) {
        if (email.length() > 254) return false;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
