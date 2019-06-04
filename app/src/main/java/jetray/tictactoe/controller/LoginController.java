package jetray.tictactoe.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import jetray.tictactoe.model.Difficult;
import jetray.tictactoe.view.main.LoginView;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class LoginController extends BaseController<LoginView> {

    private Difficult difficult;

    @Override
    public void onAttach(LoginView loginView) {
        super.onAttach(loginView);
        isAttached(view -> {
            view.showDifficulty(Arrays.stream(Difficult.values()).collect(Collectors.toList()));
        });
    }

    public void onSelectDifficult(Difficult difficult) {
        this.difficult = difficult;
    }
}
