package jetray.tictactoe;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import jetray.tictactoe.controller.game.GameController;
import jetray.tictactoe.model.Player;
import jetray.tictactoe.model.game.GameData;
import jetray.tictactoe.model.game.GameState;
import jetray.tictactoe.model.game.Step;
import jetray.tictactoe.model.login.LoginData;
import jetray.tictactoe.view.game.GameView;

public class GameActivity extends AppCompatActivity implements GameView {

    Random r = new Random();

    int flag = 0, ax = 10, zero = 1, win = 0, i, game = 1;
    int summ = 0;
    int ctrflag = 0;
    int resetchecker = 1;
    int currentgamedonechecker = 0;
    int score1 = 0;
    int score2 = 0;
    int drawchecker = 0;
    static int[][] tracker = new int[3][3];
    int[] sum = new int[8];
    static int[][] buttonpressed = new int[3][3];

    boolean player1ax;
    boolean singlePlayer;

    ImageView cell_00, cell_01, cell_02, cell_10, cell_11, cell_12, cell_20, cell_21, cell_22;

    TextView playerNameOne;
    TextView playerOneScore;

    TextView playerNameTwo;
    TextView playerTwoScore;

    TextView textStepOwner;

    GameController controller = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_afterstart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playerNameOne = findViewById(R.id.playerone);
        playerNameTwo = findViewById(R.id.playertwo);
        playerOneScore = findViewById(R.id.playerOneScore);
        playerTwoScore = findViewById(R.id.playerTwoScore);
        textStepOwner = findViewById(R.id.stepOwner);

        cell_00 = findViewById(R.id.table_00);
        cell_01 = findViewById(R.id.table_01);
        cell_02 = findViewById(R.id.table_02);
        cell_10 = findViewById(R.id.table_10);
        cell_11 = findViewById(R.id.table_11);
        cell_12 = findViewById(R.id.table_12);
        cell_20 = findViewById(R.id.table_20);
        cell_21 = findViewById(R.id.table_21);
        cell_22 = findViewById(R.id.table_22);

        List<View> views = Arrays.asList(
            cell_00,
            cell_01,
            cell_02,
            cell_10,
            cell_11,
            cell_12,
            cell_20,
            cell_21,
            cell_22
        );

        List<Callable<Step>> steps = Arrays.asList(
            () -> new Step(0, 0),
            () -> new Step(0, 1),
            () -> new Step(0, 2),
            () -> new Step(1, 0),
            () -> new Step(1, 1),
            () -> new Step(1, 2),
            () -> new Step(2, 0),
            () -> new Step(2, 1),
            () -> new Step(2, 2)
        );

        for (int i = 0; i < views.size(); i++) {
            int finalI = i;
            views.get(i).setOnClickListener(v -> {
                try {
                    controller.onPressStep(steps.get(finalI).call());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        controller.onAttach(this);
    }

    @Override
    public void render(LoginData loginData, GameData gameData) {
        playerNameOne.setText(loginData.playerOne.name);
        playerNameTwo.setText(loginData.playerTwo.name);
        playerOneScore.setText(String.valueOf(loginData.playerOne.countWin));
        playerTwoScore.setText(String.valueOf(loginData.playerTwo.countWin));

        textStepOwner.setText(gameData.stepOwner.name);

        renderTable(gameData.table);

        switch (gameData.gameState) {
            case WIN:
                Player lose = GameState.WIN.player == loginData.playerOne
                    ? loginData.playerTwo
                    : loginData.playerOne;

                showDialog(
                    gameData.gameState.player.name,
                    gameData.gameState.player.countWin,
                    lose.name,
                    lose.countWin
                );
                break;
            case DRAW:
                showDialog(
                    "Ничья!",
                    0,
                    "Ничья",
                    0
                );
                break;
            case IN_PROGRESS:
                break;
        }
    }

    private void renderTable(int[][] table) {
        if (table[0][0] == 1) cell_00.setImageResource(R.drawable.x);
        if (table[0][0] == 0) cell_00.setImageResource(R.drawable.oo);
        if (table[0][0] == -1) cell_00.setImageDrawable(null);

        if (table[0][1] == 1) cell_01.setImageResource(R.drawable.x);
        if (table[0][1] == 0) cell_01.setImageResource(R.drawable.oo);
        if (table[0][1] == -1) cell_01.setImageDrawable(null);

        if (table[0][2] == 1) cell_02.setImageResource(R.drawable.x);
        if (table[0][2] == 0) cell_02.setImageResource(R.drawable.oo);
        if (table[0][2] == -1) cell_02.setImageDrawable(null);

        if (table[1][0] == 1) cell_10.setImageResource(R.drawable.x);
        if (table[1][0] == 0) cell_10.setImageResource(R.drawable.oo);
        if (table[1][0] == -1) cell_10.setImageDrawable(null);

        if (table[1][1] == 1) cell_11.setImageResource(R.drawable.x);
        if (table[1][1] == 0) cell_11.setImageResource(R.drawable.oo);
        if (table[1][1] == -1) cell_11.setImageDrawable(null);

        if (table[1][2] == 1) cell_12.setImageResource(R.drawable.x);
        if (table[1][2] == 0) cell_12.setImageResource(R.drawable.oo);
        if (table[1][2] == -1) cell_12.setImageDrawable(null);

        if (table[2][0] == 1) cell_20.setImageResource(R.drawable.x);
        if (table[2][0] == 0) cell_20.setImageResource(R.drawable.oo);
        if (table[2][0] == -1) cell_20.setImageDrawable(null);

        if (table[2][1] == 1) cell_21.setImageResource(R.drawable.x);
        if (table[2][1] == 0) cell_21.setImageResource(R.drawable.oo);
        if (table[2][1] == -1) cell_21.setImageDrawable(null);

        if (table[2][2] == 1) cell_22.setImageResource(R.drawable.x);
        if (table[2][2] == 0) cell_22.setImageResource(R.drawable.oo);
        if (table[2][2] == -1) cell_22.setImageDrawable(null);
    }

    public void showDialog(String whoWon, int scoreWon, String whoLose, int scoreLose) {
        final Dialog dialog = new Dialog(GameActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        TextView titleText = dialog.findViewById(R.id.title_text);
        dialog.setCancelable(false);
        dialog.show();

        titleText.setText(whoWon);

        Button resetButton = dialog.findViewById(R.id.reset_button);
        Button playAgainButton = dialog.findViewById(R.id.play_again_button);

        resetButton.setOnClickListener(view -> {
            dialog.dismiss();
            controller.onGameReset();
        });

        playAgainButton.setOnClickListener(view -> {
            dialog.dismiss();
            controller.onNewGame();
        });
    }

    private void showExitDialog() {
        final Dialog dialog = new Dialog(GameActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout_exit);
        dialog.setCancelable(false);

        dialog.show();

        Button exit = dialog.findViewById(R.id.yes_button);
        final Button dismiss = dialog.findViewById(R.id.no_button);

        exit.setOnClickListener(view -> finish());

        dismiss.setOnClickListener(view -> dialog.dismiss());
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

}


