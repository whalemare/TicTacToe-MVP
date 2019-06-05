package jetray.tictactoe.model.game;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class GameData {
    public int countWinPlayerOne = 0;
    public int countWinPlayerTwo = 0;

    public int[][] table = {
        {-1, -1, -1},
        {-1, -1, -1},
        {-1, -1, -1}
    };
}
