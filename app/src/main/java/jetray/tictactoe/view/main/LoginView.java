package jetray.tictactoe.view.main;

import java.util.List;

import jetray.tictactoe.controller.LoginData;
import jetray.tictactoe.model.Difficult;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public interface LoginView {
    void showDifficulty(List<Difficult> values);

    void render(LoginData loginData);

    void routeToGame(LoginData loginData);
}
