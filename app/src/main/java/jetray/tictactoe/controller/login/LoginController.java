package jetray.tictactoe.controller.login;

import java.util.Arrays;
import java.util.stream.Collectors;

import jetray.tictactoe.controller.BaseController;
import jetray.tictactoe.model.Difficult;
import jetray.tictactoe.model.login.LoginData;
import jetray.tictactoe.model.Player;
import jetray.tictactoe.model.Sign;
import jetray.tictactoe.model.storage.MemoryStorage;
import jetray.tictactoe.view.main.LoginView;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class LoginController extends BaseController<LoginView> {

    private LoginData loginData = new LoginData(
        new Player("Игрок 1", Sign.X),
        new Player("Игрок 2", Sign.O),
        Difficult.EASY,
        true
    );

    @Override
    public void onAttach(LoginView loginView) {
        super.onAttach(loginView);
        isAttached(view -> {
            view.showDifficulty(Arrays.stream(Difficult.values()).collect(Collectors.toList()));
            render();
        });
    }

    private void render(){
        isAttached(view -> view.render(loginData));
    }

    public void onSelectDifficult(Difficult difficult) {
        loginData.difficult = difficult;
    }

    public void onChangePlayerOneName(String playerNameOne) {
        loginData.playerOne.name = playerNameOne;
        render();
    }

    public void onChangePlayerTwoName(String playerNameTwo) {
        loginData.playerTwo.name = playerNameTwo;
        render();
    }

    public void onChangePlayerOneSign(Sign sign) {
        loginData.playerOne.sign = sign;
        loginData.playerTwo.sign = sign.another();
        render();
    }

    public void onChangePlayerTwoSign(Sign sign) {
        loginData.playerTwo.sign = sign;
        loginData.playerOne.sign = sign.another();
        render();
    }

    public void onSelectSinglePlayer(boolean singlePlayer) {
        loginData.singlePlayer = singlePlayer;
        render();
    }

    public void onClickStart() {
        MemoryStorage.getInstance().loginData = loginData;
        isAttached(LoginView::routeToGame);
    }
}
