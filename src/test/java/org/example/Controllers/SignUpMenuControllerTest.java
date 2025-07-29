package org.example.Controllers;

import org.example.Controllers.MenuController.SignUpMenuController;
import org.example.Models.App;
import org.example.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpMenuControllerTest {
    private SignUpMenuController controller;

    @BeforeEach
    void setUp() {
        controller = new SignUpMenuController();
        App.clearUsers();

        String simulatedInput = "1\nblue\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    }

    @Test
    void testValidRegistration() {
        String result = controller.registerUser(
                "user123", "Password123!", "Password123!",
                "nickname", "user@example.com", "male"
        );
        assertEquals("User created successfully! You are now in login menu!", result);
        assertEquals(1, App.getUsers().size());
        User user = App.getUsers().get(0);
        assertEquals("user123", user.getUsername());
    }

    @Test
    void testInvalidUsername() {
        String result = controller.registerUser(
                "invalid username", "Password123!", "Password123!",
                "nickname", "user@example.com", "male"
        );
        assertEquals("Username is invalid", result);
    }

    @Test
    void testTakenUsername() {
        App.getUsers().add(new User("user123", "pass", "mail@mail.com", "male", "nick"));
        String result = controller.registerUser(
                "user123", "Password123!", "Password123!",
                "nickname", "user@example.com", "male"
        );
        assertEquals("Username is taken", result);
    }

    @Test
    void testInvalidPassword() {
        String result = controller.registerUser(
                "user123", "bad pass", "bad pass",
                "nickname", "user@example.com", "male"
        );
        assertEquals("Password is invalid", result);
    }

    @Test
    void testWeakPassword() {
        String result = controller.registerUser(
                "user123", "pass", "pass",
                "nickname", "user@example.com", "male"
        );
        assertEquals("Password must be at least 8 characters long and include uppercase, digit, and special character", result);
    }

    @Test
    void testPasswordMismatch() {
        String result = controller.registerUser(
                "user123", "Password123!", "DifferentPass123!",
                "nickname", "user@example.com", "male"
        );
        assertEquals("Passwords do not match", result);
    }

    @Test
    void testInvalidEmail() {
        String result = controller.registerUser(
                "user123", "Password123!", "Password123!",
                "nickname", "invalidemail", "male"
        );
        assertEquals("Email is invalid", result);
    }

    @Test
    void testInvalidGender() {
        String result = controller.registerUser(
                "user123", "Password123!", "Password123!",
                "nickname", "user@example.com", "unknown"
        );
        assertEquals("Gender is invalid", result);
    }

    @Test
    void testGenerateRandomPassword() {
        String password = controller.generateRandomPassword();
        assertNotNull(password);
        assertTrue(password.length() >= 8);
    }
}