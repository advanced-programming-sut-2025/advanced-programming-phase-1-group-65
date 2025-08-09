package org.example.Views.MenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.Timer;
import org.example.Controllers.MenuController.ProfileMenuController;
import org.example.Main;
import org.example.Models.App;
import org.example.Models.User;
import org.example.Models.Enums.Menu;
import org.example.Views.AppMenu;

public class ProfileMenuView extends ScreenAdapter implements AppMenu {

    private Stage stage;
    private Skin skin;
    private Table table;

    private Label titleLabel;
    private Label messageLabel;

    private Label usernameLabel;
    private TextField usernameField;
    private TextButton changeUsernameButton;

    private Label nicknameLabel;
    private TextField nicknameField;
    private TextButton changeNicknameButton;

    private Label emailLabel;
    private TextField emailField;
    private TextButton changeEmailButton;

    private Label genderLabel;
    private Label genderValueLabel;

    private Label gamesPlayedLabel;
    private Label mostGoldLabel;

    private TextButton logoutButton;
    private TextButton backButton;

    private Image avatarImage;
    private ImageButton[] avatarButtons;
    private final String[] avatarPaths = {
        "avatars/avatar1.png",
        "avatars/avatar2.png",
        "avatars/avatar3.png",
        "avatars/avatar4.png"
    };

    private ProfileMenuController controller;

    public ProfileMenuView() {
        controller = new ProfileMenuController();

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

        titleLabel = new Label("Profile Menu", skin);
        titleLabel.setFontScale(2f);
        table.add(titleLabel).colspan(3).padBottom(20);
        table.row();

        if (App.getCurrentUser() == null) {
            messageLabel = new Label("You must log in first to access the Profile Menu.", skin);
            messageLabel.setColor(1, 0, 0, 1);
            table.add(messageLabel).colspan(3).padBottom(20);
            table.row();

            TextButton backToLoginButton = new TextButton("Back to Login/Register Menu", skin);
            table.add(backToLoginButton).colspan(3).width(800).center();
            table.row();

            backToLoginButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    App.setCurrentMenu(Menu.LOGINREGISTERMENU);
                    Main.getInstance().setScreen(new LoginRegisterMenuView());
                }
            });

            return;
        }

        User currentUser = App.getCurrentUser();
        float buttonWidth = 500f;

        usernameLabel = new Label("Username:", skin);
        usernameField = new TextField(currentUser.getUsername(), skin);
        changeUsernameButton = new TextButton("Change Username", skin);

        nicknameLabel = new Label("Nickname:", skin);
        nicknameField = new TextField(currentUser.getNickname(), skin);
        changeNicknameButton = new TextButton("Change Nickname", skin);

        emailLabel = new Label("Email:", skin);
        emailField = new TextField(currentUser.getEmail(), skin);
        changeEmailButton = new TextButton("Change Email", skin);

        genderLabel = new Label("Gender:", skin);
        genderValueLabel = new Label(currentUser.getGender(), skin);

        gamesPlayedLabel = new Label("Games Played: " + currentUser.getGamesPlayed(), skin);
        mostGoldLabel = new Label("Highest Gold Collected: " + currentUser.getMostGoldInOneGame(), skin);

        messageLabel = new Label("", skin);

        // آواتار فعلی
        Label avatarLabel = new Label("Current Avatar:", skin);
        avatarImage = new Image(new Texture(Gdx.files.internal(currentUser.getAvatarPath())));
        avatarImage.setSize(100, 100);
        table.add(avatarLabel).left().pad(5);
        table.add(avatarImage).size(100).colspan(2).pad(5);
        table.row();

        // اطلاعات کاربر
        table.add(usernameLabel).left().pad(5);
        table.add(usernameField).width(400).pad(5);
        table.add(changeUsernameButton).width(buttonWidth).pad(5);
        table.row();

        table.add(nicknameLabel).left().pad(5);
        table.add(nicknameField).width(400).pad(5);
        table.add(changeNicknameButton).width(buttonWidth).pad(5);
        table.row();

        table.add(emailLabel).left().pad(5);
        table.add(emailField).width(400).pad(5);
        table.add(changeEmailButton).width(buttonWidth).pad(5);
        table.row();

        table.add(genderLabel).left().pad(5);
        table.add(genderValueLabel).colspan(2).pad(5);
        table.row();

        table.add(gamesPlayedLabel).left().pad(5).colspan(3);
        table.row();
        table.add(mostGoldLabel).left().pad(5).colspan(3);
        table.row();

        // انتخاب آواتار جدید
        Label selectAvatarLabel = new Label("Select New Avatar:", skin);
        table.add(selectAvatarLabel).colspan(3).padTop(15);
        table.row();

        Table avatarSelectionTable = new Table();
        avatarButtons = new ImageButton[avatarPaths.length];

        for (int i = 0; i < avatarPaths.length; i++) {
            final String path = avatarPaths[i];
            Texture texture = new Texture(Gdx.files.internal(path));
            Drawable drawable = new TextureRegionDrawable(texture);

            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = drawable;
            style.imageDown = drawable;
            avatarButtons[i] = new ImageButton(style);
            avatarButtons[i].getImage().setScaling(Scaling.fit);
            avatarButtons[i].setSize(64, 64);

            avatarSelectionTable.add(avatarButtons[i]).pad(10);

            avatarButtons[i].addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    controller.changeAvatar(path);
                    avatarImage.setDrawable(new TextureRegionDrawable(new Texture(Gdx.files.internal(path))));
                    messageLabel.setText("Avatar changed successfully.");
                    messageLabel.setColor(0, 1, 0, 1); // سبز
                }
            });
        }

        table.add(avatarSelectionTable).colspan(3);
        table.row();

        logoutButton = new TextButton("Logout", skin);
        table.add(logoutButton).colspan(3).width(buttonWidth).center().padTop(30);
        table.row();

        backButton = new TextButton("Back to Main Menu", skin);
        table.add(backButton).colspan(3).width(buttonWidth).center().padTop(10);
        table.row();

        table.add(messageLabel).colspan(3).padTop(20);

        // تغییر نام کاربری
        changeUsernameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String newUsername = usernameField.getText().trim();
                if (newUsername.isEmpty()) {
                    messageLabel.setText("Username cannot be empty.");
                    messageLabel.setColor(1, 0, 0, 1);
                    return;
                }
                String result = controller.changeUsername(newUsername);
                messageLabel.setText(result);
                messageLabel.setColor(result.toLowerCase().contains("success") ? 0 : 1,
                    result.toLowerCase().contains("success") ? 1 : 0,
                    0, 1);
                usernameField.setText(App.getCurrentUser().getUsername());
            }
        });

        // تغییر لقب
        changeNicknameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String newNickname = nicknameField.getText().trim();
                if (newNickname.isEmpty()) {
                    messageLabel.setText("Nickname cannot be empty.");
                    messageLabel.setColor(1, 0, 0, 1);
                    return;
                }
                String result = controller.changeNickname(newNickname);
                messageLabel.setText(result);
                messageLabel.setColor(result.toLowerCase().contains("success") ? 0 : 1,
                    result.toLowerCase().contains("success") ? 1 : 0,
                    0, 1);
                nicknameField.setText(App.getCurrentUser().getNickname());
            }
        });

        // تغییر ایمیل
        changeEmailButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String newEmail = emailField.getText().trim();
                if (newEmail.isEmpty()) {
                    messageLabel.setText("Email cannot be empty.");
                    messageLabel.setColor(1, 0, 0, 1);
                    return;
                }
                String result = controller.changeEmail(newEmail);
                messageLabel.setText(result);
                messageLabel.setColor(result.toLowerCase().contains("success") ? 0 : 1,
                    result.toLowerCase().contains("success") ? 1 : 0,
                    0, 1);
                emailField.setText(App.getCurrentUser().getEmail());
            }
        });

        // خروج
        logoutButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.logout();
                messageLabel.setText("Logged out successfully. Returning to Login/Register menu...");
                messageLabel.setColor(0, 1, 0, 1); // سبز
                Main.getInstance().setScreen(new LoginRegisterMenuView());
            }
        });

        // برگشت به منوی اصلی
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getInstance().setScreen(new MainMenuView());
            }
        });
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
    public void check(java.util.Scanner scanner) {
        // برای حالت گرافیکی نیازی نیست پیاده‌سازی شود
    }
}
