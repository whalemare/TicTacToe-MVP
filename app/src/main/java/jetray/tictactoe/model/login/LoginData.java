package jetray.tictactoe.model.login;

import jetray.tictactoe.model.Difficult;
import jetray.tictactoe.model.Player;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class LoginData {
    public Player playerOne;
    public Player playerTwo;
    public Difficult difficult;
    public boolean singlePlayer;

    public LoginData(Player playerOne, Player playerTwo, Difficult difficult, boolean singlePlayer) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.difficult = difficult;
        this.singlePlayer = singlePlayer;
    }
}
