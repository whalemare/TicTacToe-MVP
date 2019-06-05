package jetray.tictactoe.controller.game;

import android.util.Log;

import jetray.tictactoe.controller.BaseController;
import jetray.tictactoe.model.game.GameInteractor;
import jetray.tictactoe.model.game.GameData;
import jetray.tictactoe.model.game.Step;
import jetray.tictactoe.model.login.LoginData;
import jetray.tictactoe.model.storage.MemoryStorage;
import jetray.tictactoe.view.game.GameView;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class GameController extends BaseController<GameView> {

    private LoginData loginData = MemoryStorage.getInstance().loginData;
    private GameData gameData = new GameData(loginData.playerOne);
    private GameInteractor gameInteractor = new GameInteractor();

    @Override
    public void onAttach(GameView gameView) {
        super.onAttach(gameView);
        gameView.render(loginData, gameData);
    }


    public void onPressStep(Step step) {
        Log.d("tag", "onPressStep: player: " + gameData.stepOwner + ";" + step.x + ":" + step.y);
        gameData.table = gameInteractor.makeStep(gameData.table, gameData.stepOwner, step);
        gameData.stepOwner = gameInteractor.nextPlayer(loginData, gameData.stepOwner);
        isAttached(v -> v.render(loginData, gameData));
    }
}
