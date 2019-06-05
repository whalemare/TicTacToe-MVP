package jetray.tictactoe.view.game;

import jetray.tictactoe.model.game.GameData;
import jetray.tictactoe.model.login.LoginData;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public interface GameView {
    void render(LoginData loginData, GameData gameData);
}
