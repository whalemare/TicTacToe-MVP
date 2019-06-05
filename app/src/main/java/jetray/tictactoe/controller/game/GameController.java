package jetray.tictactoe.controller.game;

import jetray.tictactoe.controller.BaseController;
import jetray.tictactoe.model.game.GameData;
import jetray.tictactoe.model.login.LoginData;
import jetray.tictactoe.model.storage.MemoryStorage;
import jetray.tictactoe.view.game.GameView;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class GameController extends BaseController<GameView> {

    private LoginData loginData = MemoryStorage.getInstance().loginData;
    private GameData gameData = new GameData();

    @Override
    public void onAttach(GameView gameView) {
        super.onAttach(gameView);
        gameView.render(loginData, gameData);
    }


}
