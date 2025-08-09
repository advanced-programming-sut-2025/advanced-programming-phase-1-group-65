package org.example.Views.MenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.example.Main;
import org.example.Controllers.MenuController.SignUpMenuController;
import org.example.Models.Game;
import org.example.Models.Player;
import org.example.Models.User;
import org.example.Views.AppMenu;
import org.example.Views.GameView.GameScreen;

import java.util.Scanner;

public class SignUpMenuView extends ScreenAdapter implements AppMenu {

    private Stage stage;
    private Skin skin;
    private Table table;

    private TextField usernameField, passwordField, confirmPasswordField, nicknameField, emailField, genderField;
    private Label messageLabel;
    private TextButton signUpButton, backButton, generatePasswordButton,StartTest;

    private SignUpMenuController controller;

    public SignUpMenuView() {
        controller = new SignUpMenuController();

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

        Label titleLabel = new Label("Sign Up Menu", skin);
        table.add(titleLabel).colspan(2).center().padBottom(20);
        table.row();

        messageLabel = new Label("", skin);

        usernameField = new TextField("", skin);

        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        confirmPasswordField = new TextField("", skin);
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        nicknameField = new TextField("", skin);
        emailField = new TextField("", skin);
        genderField = new TextField("", skin);

        signUpButton = new TextButton("Sign Up", skin);
        backButton = new TextButton("Back to Login/Register Menu", skin);
        generatePasswordButton = new TextButton("Generate", skin);
        StartTest = new TextButton("Start Test", skin);

        table.add(new Label("Username:", skin)).right().padRight(10);
        table.add(usernameField).width(500).padBottom(10);
        table.row();

        table.add(new Label("Password:", skin)).right().padRight(30);
        Table passwordTable = new Table();
        passwordTable.add(passwordField).width(300).padRight(5);
        passwordTable.add(generatePasswordButton).width(200);
        table.add(passwordTable).padBottom(10);
        table.row();

        table.add(new Label("Confirm Password:", skin)).right().padRight(10);
        table.add(confirmPasswordField).width(500).padBottom(10);
        table.row();

        table.add(new Label("Nickname:", skin)).right().padRight(10);
        table.add(nicknameField).width(500).padBottom(10);
        table.row();

        table.add(new Label("Email:", skin)).right().padRight(10);
        table.add(emailField).width(500).padBottom(10);
        table.row();

        table.add(new Label("Gender (male/female):", skin)).right().padRight(10);
        table.add(genderField).width(500).padBottom(10);
        table.row();

        table.add(signUpButton).colspan(2).padTop(10);
        table.row();
        table.add(backButton).colspan(2).padTop(10);
        table.row();

        table.add(messageLabel).colspan(2).padTop(10);
        table.row();
        table.add(StartTest).colspan(2).padTop(10);
        StartTest.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                User user1 = new User("Arya", "Arya@9057" , "Arya@gmail.com" , "Male" , "Arya");
                user1.player = new Player();
                user1.player.FarmNumber =1;
                User user2 = new User("Aryo", "Aryo@9057" , "Aryo@gmail.com" , "Male" , "Aryo");
                user2.player = new Player();
                user2.player.FarmNumber =2;
                User user3 = new User("Aryi", "Aryi@9057" , "Aryi@gmail.com" , "Male" , "Aryi");
                user3.player = new Player();
                user3.player.FarmNumber =3;
                Game TestGame = new Game(user1, user2, user3);

                user1.player.game = TestGame;
                user2.player.game = TestGame;
                user3.player.game = TestGame;
                TestGame.currentPlayer=user3.player;
                TestGame.GameRun();

                GameScreen gameScreen = new GameScreen(TestGame);
                TestGame.gameScreen = gameScreen;
                Gdx.app.postRunnable(() -> Main.getInstance().setScreen(gameScreen));



            }

        });

        signUpButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                messageLabel.setText("");
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                String confirmPassword = confirmPasswordField.getText().trim();
                String nickname = nicknameField.getText().trim();
                String email = emailField.getText().trim();
                String gender = genderField.getText().trim();

                String validationResult = controller.registerUser(
                    username, password, confirmPassword, nickname, email, gender, 0, null);

                if (!validationResult.startsWith("User created successfully")) {
                    messageLabel.setColor(1, 0, 0, 1);
                    messageLabel.setText(validationResult);
                    return;
                }
                showSecurityQuestionDialog(username, password, confirmPassword, nickname, email, gender);
            }
        });


        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getInstance().setScreen(new LoginRegisterMenuView());
            }
        });

        generatePasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String generatedPassword = controller.generateRandomPassword();

                Dialog dialog = new Dialog("Random Password", skin) {
                    @Override
                    protected void result(Object object) {
                        if ((Boolean) object) {
                            passwordField.setText(generatedPassword);
                            confirmPasswordField.setText(generatedPassword);
                            messageLabel.setColor(0, 1, 0, 1);
                            messageLabel.setText("Random password used.");
                        } else {
                            messageLabel.setColor(1, 1, 0, 1);
                            messageLabel.setText("Random password canceled.");
                        }
                    }
                };

                dialog.text("Generated password:\n" + generatedPassword);
                dialog.button("Use This Password", true);
                dialog.button("Cancel", false);
                dialog.show(stage);
            }
        });
    }

    private void showSecurityQuestionDialog(String username, String password, String confirmPassword,
                                            String nickname, String email, String gender) {
        Dialog securityDialog = new Dialog("Security Question", skin) {
            private SelectBox<String> questionBox;
            private TextField answerField;

            {
                pad(20);
                questionBox = new SelectBox<>(skin);
                questionBox.setItems(
                    "What is your favorite color?",
                    "What was your first school's name?",
                    "In which city were you born?"
                );

                answerField = new TextField("", skin);
                answerField.setMessageText("Enter your answer");

                getContentTable().add(new Label("Select a question:", skin)).padBottom(5).row();
                getContentTable().add(questionBox).width(800).padBottom(10).row();
                getContentTable().add(new Label("Your Answer:", skin)).padBottom(5).row();
                getContentTable().add(answerField).width(800).padBottom(20).row();

                button("Submit", true);
                button("Cancel", false);
            }

            @Override
            protected void result(Object obj) {
                boolean submit = (Boolean) obj;
                if (submit) {
                    String selectedQuestion = questionBox.getSelected();
                    String answer = answerField.getText().trim();

                    if (answer.isEmpty()) {
                        messageLabel.setColor(1, 0, 0, 1);
                        messageLabel.setText("Security answer cannot be empty.");
                        return;
                    }

                    int questionNumber = switch (selectedQuestion) {
                        case "What is your favorite color?" -> 1;
                        case "What was your first school's name?" -> 2;
                        case "In which city were you born?" -> 3;
                        default -> 0;
                    };

                    String finalResult = controller.registerUser(
                        username, password, confirmPassword,
                        nickname, email, gender,
                        questionNumber, answer
                    );

                    if (finalResult.startsWith("User created successfully")) {
                        messageLabel.setColor(0, 1, 0, 1);
                        messageLabel.setText(finalResult);
                        this.hide();
                    } else {
                        messageLabel.setColor(1, 0, 0, 1);
                        messageLabel.setText(finalResult);
                    }
                } else {
                    messageLabel.setColor(1, 1, 0, 1);
                    messageLabel.setText("Registration cancelled.");
                    this.hide();
                }
            }
        };

        securityDialog.show(stage).setMovable(false);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT);
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
