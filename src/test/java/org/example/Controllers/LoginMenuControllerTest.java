package org.example.Controllers;

import org.example.Controllers.MenuController.LoginMenuController;
import org.example.Models.App;
import org.example.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class LoginMenuControllerTest {

    private LoginMenuController controller;

    @BeforeEach
    void setUp() {
        controller = new LoginMenuController();
        App.getUsers().clear();
    }

    @Test
    void testValidLogin() {
        User user = new User("user123", "Password123!", "user@example.com", "male", "nickname");
        App.addUser(user);
        boolean result = controller.login("user123", "Password123!" ,false);

        assertTrue(result);
        assertEquals(user, App.getCurrentUser());
    }

    @Test
    void testInvalidLogin() {
        User user = new User("user123", "Password123!", "user@example.com", "male", "nickname");
        App.addUser(user);
        boolean result = controller.login("user123", "WrongPassword!" , false);

        assertFalse(result);
    }

    @Test
    void testForgotPasswordUserNotFound() {
        String result = controller.forgotPassword("nonexistentUser");

        assertEquals("Username not found!", result);
    }

    @Test
    void testForgotPasswordIncorrectSecurityAnswer() {

        User user = new User("user123", "Password123!", "user@example.com", "male", "nickname");
        user.setAnswer("blue", 1);
        App.addUser(user);

        String wrongAnswer = "WrongColor";
        InputStream in = new ByteArrayInputStream(wrongAnswer.getBytes());
        System.setIn(in);

        String result = controller.forgotPassword("user123");

        assertTrue(result.startsWith("Incorrect answer"));
    }

    @Test
    void testForgotPassword() {

        User user = new User("user123", "Password123!", "user@example.com", "male", "nickname");
        user.setAnswer("blue", 1); // Set security answer
        App.addUser(user);

        String input = "blue\nNewPassword123!\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String result = controller.forgotPassword("user123");

        assertEquals("Password has been successfully reset!", result);
        assertEquals("NewPassword123!", user.getPassword());

        input = "wrongAnswer\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        result = controller.forgotPassword("user123");

        assertEquals("Incorrect answer. Returning to the login menu...", result);
    }

    @Test
    void testGenerateRandomPassword() {
        String password = controller.generateRandomPassword();
        assertNotNull(password);
        assertTrue(password.length() >= 8);
        assertTrue(controller.isPasswordStrong(password));
    }

    @Test
    void testInvalidPasswordFormat() {
        String invalidPassword = "password";
        assertFalse(controller.isPasswordStrong(invalidPassword));
    }

    @Test
    void testValidPasswordFormat() {
        String validPassword = "Password123!";
        assertTrue(controller.isPasswordStrong(validPassword));
    }
}