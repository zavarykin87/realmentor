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
    }
}