package jetray.tictactoe.model.game;

import jetray.tictactoe.model.Player;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class GameData {

    public GameState gameState = GameState.IN_PROGRESS;

    public int[][] table = {
        {-1, -1, -1},
        {-1, -1, -1},
        {-1, -1, -1}
    };

    public Player stepOwner;


    public GameData(Player stepOwner) {
        this.stepOwner = stepOwner;
    }

}
