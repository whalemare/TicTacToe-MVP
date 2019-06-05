package jetray.tictactoe.controller.game;

import android.util.Log;

import jetray.tictactoe.controller.BaseController;
import jetray.tictactoe.model.game.GameData;
import jetray.tictactoe.model.game.GameInteractor;
import jetray.tictactoe.model.game.GameState;
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

        if (gameInteractor.isGameFinished(gameData.table)) {
            gameData.gameState = gameInteractor.checkGameResult(gameData.table, gameData.stepOwner);
        } else {
            gameData.stepOwner = gameInteractor.nextPlayer(loginData, gameData.stepOwner);
        }
        render();
    }

    public void onGameReset() {
        loginData.playerOne.countWin = 0;
        loginData.playerTwo.countWin = 0;
        onNewGame();
    }

    public void onNewGame() {
        gameData.table = gameInteractor.newTable();
        gameData.gameState = GameState.IN_PROGRESS;
        gameData.stepOwner = gameData.stepOwner.sign == loginData.playerOne.sign
            ? loginData.playerTwo
            : loginData.playerOne;
        render();
    }

    private void render() {
        isAttached(v -> v.render(loginData, gameData));
    }
}
