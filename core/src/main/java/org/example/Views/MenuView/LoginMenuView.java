package org.example.Views.MenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.example.Main;
import org.example.Models.App;
import org.example.Models.User;
import org.example.Models.Enums.Menu;
import org.example.Views.AppMenu;
import org.example.Controllers.MenuController.LoginMenuController;

import java.util.Scanner;

public class LoginMenuView extends ScreenAdapter implements AppMenu {

    private Stage stage;
    private Skin skin;
    private Table table;

    private TextField usernameField;
    private TextField passwordField;
    private CheckBox stayLoggedInCheckBox;
    private Label messageLabel;
    private TextButton loginButton;
    private TextButton forgotPasswordButton;
    private TextButton backButton;
    private Window forgotPasswordWindow;
    private Label securityQuestionLabel;
    private TextField securityAnswerField;
    private TextField newPasswordField;
    private TextButton resetPasswordButton;
    private TextButton generateRandomPasswordButton;
    private Label forgotPasswordMessageLabel;

    private LoginMenuController controller;

    private User forgotPasswordUser;

    public LoginMenuView() {
        controller = new LoginMenuController();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        table = new Table();
        table.setFillParent(true);
        Texture background = new Texture(Gdx.files.internal("background.png"));
        Image backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);
        stage.addActor(table);

        messageLabel = new Label("", skin);
        messageLabel.setColor(1, 0, 0, 1);

        Label titleLabel = new Label("Login Menu", skin);
        usernameField = new TextField("", skin);
        usernameField.setMessageText("Username");
        passwordField = new TextField("", skin);
        passwordField.setMessageText("Password");
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);

        stayLoggedInCheckBox = new CheckBox("Stay Logged In", skin);

        loginButton = new TextButton("Login", skin);
        forgotPasswordButton = new TextButton("Forgot Password", skin);

        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                doLogin();
            }
        });

        forgotPasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                openForgotPasswordWindow();
            }
        });
        backButton = new TextButton("Back to Login/Register Menu", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getInstance().setScreen(new LoginRegisterMenuView());
            }
        });

        table.add(titleLabel).colspan(2).padBottom(20);
        table.row();
        table.add(new Label("Username:", skin)).left();
        table.add(usernameField).width(800).padBottom(10).padLeft(-300);
        table.row();
        table.add(new Label("Password:", skin)).left();
        table.add(passwordField).width(800).left(). padBottom(10).padLeft(-300);
        table.row();
        table.add(stayLoggedInCheckBox).colspan(2).center().padBottom(10);
        table.row();
        table.add(loginButton).width(500).padRight(10);
        table.add(forgotPasswordButton).width(500);
        table.row();
        table.add(messageLabel).colspan(2).padTop(20);
        table.row();
        table.add(backButton).colspan(2).width(800).padTop(30).center();
        forgotPasswordWindow = new Window("Forgot Password", skin);
        forgotPasswordWindow.setModal(true);
        forgotPasswordWindow.setVisible(false);
        forgotPasswordWindow.setSize(800, 800);

        securityQuestionLabel = new Label("", skin);
        securityAnswerField = new TextField("", skin);
        securityAnswerField.setMessageText("Your Answer");

        newPasswordField = new TextField("", skin);
        newPasswordField.setMessageText("New Password");

        resetPasswordButton = new TextButton("Reset Password", skin);
        generateRandomPasswordButton = new TextButton("Generate Random Password", skin);

        forgotPasswordMessageLabel = new Label("", skin);
        forgotPasswordMessageLabel.setColor(1, 0, 0, 1);
        forgotPasswordWindow.add(securityQuestionLabel).colspan(2).pad(10);
        forgotPasswordWindow.row();
        forgotPasswordWindow.add(securityAnswerField).colspan(2).width(350).pad(10);
        forgotPasswordWindow.row();
        forgotPasswordWindow.add(newPasswordField).colspan(2).width(350).pad(10);
        forgotPasswordWindow.row();
        forgotPasswordWindow.add(resetPasswordButton).width(500).pad(10).center();
        forgotPasswordWindow.row();
        forgotPasswordWindow.add(generateRandomPasswordButton).width(800).pad(10).center();
        forgotPasswordWindow.row();
        forgotPasswordWindow.add(forgotPasswordMessageLabel).colspan(2).pad(10);

        stage.addActor(forgotPasswordWindow);
        forgotPasswordWindow.pack();
        forgotPasswordWindow.setPosition(
            (stage.getWidth() - forgotPasswordWindow.getWidth()) / 2,
            (stage.getHeight() - forgotPasswordWindow.getHeight()) / 2
        );

        resetPasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                resetPassword();
            }
        });

        generateRandomPasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String randomPass = controller.generateRandomPassword();
                newPasswordField.setText(randomPass);
                forgotPasswordMessageLabel.setText("Random password generated.");
            }
        });
    }

    private void doLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        boolean stayLoggedIn = stayLoggedInCheckBox.isChecked();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter both username and password.");
            return;
        }

        boolean success = controller.login(username, password, stayLoggedIn);
        if (success) {
            messageLabel.setColor(0, 1, 0, 1);
            messageLabel.setText("Login successful!");
            App.setCurrentMenu(Menu.MAINMENU);
        } else {
            messageLabel.setColor(1, 0, 0, 1);
            messageLabel.setText("Incorrect username or password!");
        }
    }

    private void openForgotPasswordWindow() {
        String username = usernameField.getText().trim();

        if (username.isEmpty()) {
            messageLabel.setColor(1, 0, 0, 1);
            messageLabel.setText("Please enter your username first.");
            return;
        }

        forgotPasswordUser = null;
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username)) {
                forgotPasswordUser = user;
                break;
            }
        }

        if (forgotPasswordUser == null) {
            messageLabel.setColor(1, 0, 0, 1);
            messageLabel.setText("Username not found.");
            return;
        }

        securityQuestionLabel.setText(controller.getSecurityQuestion(forgotPasswordUser.getAnswerNumber()));

        securityAnswerField.setText("");
        newPasswordField.setText("");
        forgotPasswordMessageLabel.setText("");
        forgotPasswordWindow.setVisible(true);
        stage.setKeyboardFocus(securityAnswerField);
    }

    private void resetPassword() {
        if (forgotPasswordUser == null) {
            forgotPasswordMessageLabel.setText("No user selected.");
            return;
        }

        String answer = securityAnswerField.getText().trim();
        String newPassword = newPasswordField.getText().trim();

        if (answer.isEmpty() || newPassword.isEmpty()) {
            forgotPasswordMessageLabel.setText("Please answer the security question and enter new password.");
            return;
        }

        if (!controller.checkSecurityAnswer(forgotPasswordUser, answer)) {
            forgotPasswordMessageLabel.setColor(1, 0, 0, 1);
            forgotPasswordMessageLabel.setText("Incorrect security answer.");
            return;
        }

        if (!controller.isPasswordValid(newPassword)) {
            forgotPasswordMessageLabel.setColor(1, 0, 0, 1);
            forgotPasswordMessageLabel.setText("Password contains invalid characters.");
            return;
        }

        if (!controller.isPasswordStrong(newPassword)) {
            forgotPasswordMessageLabel.setColor(1, 0, 0, 1);
            forgotPasswordMessageLabel.setText("Password too weak: min 8 chars, uppercase, digit & special char.");
            return;
        }

        forgotPasswordUser.setPassword(newPassword);
        forgotPasswordMessageLabel.setColor(0, 1, 0, 1);
        forgotPasswordMessageLabel.setText("Password successfully reset!");

        forgotPasswordWindow.setVisible(false);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    @Override
    public void check(Scanner scanner) {
    }
}

