package jetray.tictactoe.model.storage;

import jetray.tictactoe.model.login.LoginData;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class MemoryStorage {

    private static MemoryStorage memoryStorage;

    public static MemoryStorage getInstance(){
        if (memoryStorage == null) {
            memoryStorage = new MemoryStorage();
        }
        return memoryStorage;
    }

    public LoginData loginData;

}
