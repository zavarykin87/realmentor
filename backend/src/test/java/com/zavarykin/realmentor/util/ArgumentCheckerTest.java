package com.zavarykin.realmentor.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentCheckerTest {

    @Test
    void checkUserLogin_ShouldReturnTrue() {
        String login1 = "login";
        String login2 = "login123";
        String login3 = "login-123";
        String login4 = "login_123";
        String login5 = "login.123";

        assertTrue(ArgumentChecker.checkUserLogin(login1));
        assertTrue(ArgumentChecker.checkUserLogin(login2));
        assertTrue(ArgumentChecker.checkUserLogin(login3));
        assertTrue(ArgumentChecker.checkUserLogin(login4));
        assertTrue(ArgumentChecker.checkUserLogin(login5));
    }

    @Test
    void checkUserLogin_ShouldReturnFalse() {
        String login1 = "         ";
        String login2 = ".login";
        String login3 = "-login";
        String login4 = "_login";
        String login5 = "login_";
        String login6 = "login-";
        String login7 = "login.";
        String login8 = "lo";
        String login9 = "loguyyyyyyyyyyyyyyyyy";
        String login10 = "login!";
        String login11 = "login 123";

        assertFalse(ArgumentChecker.checkUserLogin(login1));
        assertFalse(ArgumentChecker.checkUserLogin(login2));
        assertFalse(ArgumentChecker.checkUserLogin(login3));
        assertFalse(ArgumentChecker.checkUserLogin(login4));
        assertFalse(ArgumentChecker.checkUserLogin(login5));
        assertFalse(ArgumentChecker.checkUserLogin(login6));
        assertFalse(ArgumentChecker.checkUserLogin(login7));
        assertFalse(ArgumentChecker.checkUserLogin(login8));
        assertFalse(ArgumentChecker.checkUserLogin(login9));
        assertFalse(ArgumentChecker.checkUserLogin(login10));
        assertFalse(ArgumentChecker.checkUserLogin(login11));
    }

    @Test
    void checkUserPassword_ShouldReturnTrue() {
        String password = "Pass@123";
        assertTrue(ArgumentChecker.checkUserPassword(password));
    }

    @Test
    void checkUserPassword_ShouldReturnFalse() {
        String password1 = "Pass";
        String password2 = "Password";
        String password3 = "Password123";
        String password4 = "Password@ 123";
        String password5 = "Password@\t123";
        String password6 = "Password@\n123";
        String password7 = "Мойпароль123!";
        String password8 = "Password@123112222222222222222222";

        assertFalse(ArgumentChecker.checkUserPassword(password1));
        assertFalse(ArgumentChecker.checkUserPassword(password2));
        assertFalse(ArgumentChecker.checkUserPassword(password3));
        assertFalse(ArgumentChecker.checkUserPassword(password4));
        assertFalse(ArgumentChecker.checkUserPassword(password5));
        assertFalse(ArgumentChecker.checkUserPassword(password6));
        assertFalse(ArgumentChecker.checkUserPassword(password7));
        assertFalse(ArgumentChecker.checkUserPassword(password8));
    }

    @Test
    void checkUserEmail_ShouldReturnTrue() {
        String email1 = "user@example.com";
        String email2 = "user.name+tag@example.com";
        String email3 = "user@sub.example.co.ru";

        assertTrue(ArgumentChecker.checkUserEmail(email1));
        assertTrue(ArgumentChecker.checkUserEmail(email2));
        assertTrue(ArgumentChecker.checkUserEmail(email3));

    }

    @Test
    void checkUserEmail_ShouldReturnFalse() {
        String email1 = "user@.com";
        String email2 = "user@example";
        String email3 = " username@example.com";

        assertFalse(ArgumentChecker.checkUserEmail(email1));
        assertFalse(ArgumentChecker.checkUserEmail(email2));
        assertFalse(ArgumentChecker.checkUserEmail(email3));

    }
}