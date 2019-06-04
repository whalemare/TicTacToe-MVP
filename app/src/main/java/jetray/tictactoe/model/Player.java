package jetray.tictactoe.model;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class Player {

    public String name;
    public Sign sign;

    public Player(String name, Sign sign) {
        this.name = name;
        this.sign = sign;
    }
}
