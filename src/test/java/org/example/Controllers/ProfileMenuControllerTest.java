package org.example.Controllers;

import org.example.Models.App;
import org.example.Models.Enums.Menu;
import org.example.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileMenuControllerTest {

    private ProfileMenuController controller;
    private User user;

    @BeforeEach
    void setUp() {
        controller = new ProfileMenuController();
        App.getUsers().clear();

        user = new User("testuser", "OldPass123!", "test@example.com", "male", "nickname");
        App.addUser(user);
        App.setCurrentUser(user);
    }

    @Test
    void testChangeUsernameSuccessfully() {
        controller.changeUsername("newuser");
        assertEquals("newuser", App.getCurrentUser().getUsername());
    }

    @Test
    void testChangeUsernameToSame() {
        controller.changeUsername("testuser");
        assertEquals("testuser", App.getCurrentUser().getUsername());
    }

    @Test
    void testChangeUsernameToInvalid() {
        controller.changeUsername("invalid@name!");
        assertEquals("testuser", App.getCurrentUser().getUsername());
    }

    @Test
    void testChangeUsernameToTaken() {
        App.addUser(new User("takenUser", "pass", "email2@example.com", "male", "nick"));
        controller.changeUsername("takenUser");
        assertEquals("testuser", App.getCurrentUser().getUsername());
    }

    @Test
    void testChangePasswordSuccessfully() {
        controller.changePassword("OldPass123!", "NewPass123!");
        assertEquals("NewPass123!", App.getCurrentUser().getPassword());
    }

    @Test
    void testChangePasswordWithWrongOldPassword() {
        controller.changePassword("WrongOldPass", "NewPass123!");
        assertEquals("OldPass123!", App.getCurrentUser().getPassword());
    }

    @Test
    void testChangePasswordToWeakOne() {
        controller.changePassword("OldPass123!", "weak");
        assertEquals("OldPass123!", App.getCurrentUser().getPassword());
    }

    @Test
    void testChangePasswordToSame() {
        controller.changePassword("OldPass123!", "OldPass123!");
        assertEquals("OldPass123!", App.getCurrentUser().getPassword());
    }

    @Test
    void testChangeEmailSuccessfully() {
        controller.changeEmail("newemail@example.com");
        assertEquals("newemail@example.com", App.getCurrentUser().getEmail());
    }

    @Test
    void testChangeEmailToSame() {
        controller.changeEmail("test@example.com");
        assertEquals("test@example.com", App.getCurrentUser().getEmail());
    }

    @Test
    void testChangeEmailToInvalidFormat() {
        controller.changeEmail("invalidemail");
        assertEquals("test@example.com", App.getCurrentUser().getEmail());
    }

    @Test
    void testChangeEmailToTaken() {
        App.addUser(new User("another", "pass", "taken@example.com", "male", "nick"));
        controller.changeEmail("taken@example.com");
        assertEquals("test@example.com", App.getCurrentUser().getEmail());
    }

    @Test
    void testChangeNicknameSuccessfully() {
        controller.changeNickname("newNick");
        assertEquals("newNick", App.getCurrentUser().getNickname());
    }

    @Test
    void testChangeNicknameToSame() {
        controller.changeNickname("nickname");
        assertEquals("nickname", App.getCurrentUser().getNickname());
    }

    @Test
    void testLogout() {
        controller.logout();
        assertNull(App.getCurrentUser());
        assertEquals(Menu.LOGINMENU, App.getCurrentMenu());
    }

    @Test
    void testDisplayUserInfoWithNoUser() {
        App.setCurrentUser(null);
        controller.displayUserInfo();
    }
}