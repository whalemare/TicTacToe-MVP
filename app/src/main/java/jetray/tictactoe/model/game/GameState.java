package jetray.tictactoe.model.game;

import jetray.tictactoe.model.Player;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public enum GameState {
    WIN,
    DRAW,
    IN_PROGRESS;

    // sry for this, I cant use sealed classes and not have enough time for improve it
    public Player player;
}
