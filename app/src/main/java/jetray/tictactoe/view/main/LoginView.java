package jetray.tictactoe.view.main;

import java.util.List;

import jetray.tictactoe.model.login.LoginData;
import jetray.tictactoe.model.Difficult;

/**
 * @author Irina Ivanova
 * @since 2019
 */
public interface LoginView {
    void showDifficulty(List<Difficult> values);

    void render(LoginData loginData);

    void routeToGame();
}
