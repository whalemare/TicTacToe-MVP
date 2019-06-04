package jetray.tictactoe.controller;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public interface Invokable<Item> {
    void invoke(Item item);
}
