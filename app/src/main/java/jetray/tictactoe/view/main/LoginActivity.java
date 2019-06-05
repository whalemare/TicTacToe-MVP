package jetray.tictactoe.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jetray.tictactoe.GameActivity;
import jetray.tictactoe.R;
import jetray.tictactoe.utils.Utils;
import jetray.tictactoe.controller.login.LoginController;
import jetray.tictactoe.model.login.LoginData;
import jetray.tictactoe.model.Difficult;
import jetray.tictactoe.model.Sign;

public class LoginActivity extends AppCompatActivity implements LoginView {

    public EditText playerOne;
    public EditText playerTwo;
    public Spinner difficulty;
    public CheckBox p1x, p1o, p2x, p2o, singleplayer, twoplayer;

    private LoginController controller = new LoginController();

    View.OnClickListener checkboxClickListener = view -> {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.player1x:
                controller.onChangePlayerOneSign(checked ? Sign.X : Sign.O);
                break;
            case R.id.player1o:
                controller.onChangePlayerOneSign(checked ? Sign.O : Sign.X);
                break;
            case R.id.player2x:
                controller.onChangePlayerTwoSign(checked ? Sign.X : Sign.O);
                break;
            case R.id.player2o:
                controller.onChangePlayerTwoSign(checked ? Sign.O : Sign.X);
                break;
            case R.id.splayer:
                controller.onSelectSinglePlayer(checked);
                break;
            case R.id.tplayer:
                controller.onSelectSinglePlayer(!checked);
                break;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        playerOne = findViewById(R.id.playerone);
        playerTwo = findViewById(R.id.playertwo);

        p1x = findViewById(R.id.player1x);
        p1o = findViewById(R.id.player1o);
        p2x = findViewById(R.id.player2x);
        p2o = findViewById(R.id.player2o);
        singleplayer = findViewById(R.id.splayer);
        twoplayer = findViewById(R.id.tplayer);
        findViewById(R.id.start).setOnClickListener(view -> {
            controller.onClickStart();
        });

        controller.onAttach(this);
    }


    @Override
    public void showDifficulty(List<Difficult> list) {
        difficulty = findViewById(R.id.difficulty);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            list.stream().map(difficult -> difficult.title).collect(Collectors.toList())
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(dataAdapter);


        difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();
                Difficult difficult = Arrays.stream(Difficult.values())
                    .filter(diff -> diff.title.equals(temp))
                    .findFirst()
                    .get();
                controller.onSelectDifficult(difficult);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                controller.onSelectDifficult(Difficult.EASY);
            }
        });
    }

    @Override
    public void render(LoginData loginData) {
        enableListeners(false);
        int cursorPosition = playerOne.getSelectionStart();
        int cursorPositionEnd = playerOne.getSelectionEnd();
        playerOne.setText(loginData.playerOne.name);
        playerOne.setSelection(cursorPosition, cursorPositionEnd);

        cursorPosition = playerTwo.getSelectionStart();
        cursorPositionEnd = playerTwo.getSelectionEnd();
        playerTwo.setText(loginData.playerTwo.name);
        playerTwo.setSelection(cursorPosition, cursorPositionEnd);

        p1x.setChecked(loginData.playerOne.sign == Sign.X);
        p1o.setChecked(loginData.playerOne.sign == Sign.O);
        p2x.setChecked(loginData.playerTwo.sign == Sign.X);
        p2o.setChecked(loginData.playerTwo.sign == Sign.O);

        difficulty.setSelection(Utils.indexOf(
            Arrays.asList(Difficult.values()),
            diff -> diff == loginData.difficult
        ));

        singleplayer.setChecked(loginData.singlePlayer);
        twoplayer.setChecked(!loginData.singlePlayer);
        enableListeners(true);
    }

    private TextWatcher firstWatcher;
    private TextWatcher secondWatcher;
    private void enableListeners(boolean enabled) {
        if (enabled) {
            firstWatcher = Utils.onTextChanges(playerOne, text -> {
                controller.onChangePlayerOneName(text);
            });
        } else {
            playerOne.removeTextChangedListener(firstWatcher);
        }

        if (enabled) {
            secondWatcher = Utils.onTextChanges(playerTwo, text -> {
                controller.onChangePlayerTwoName(text);
            });
        } else {
            playerTwo.removeTextChangedListener(secondWatcher);
        }
        p1x.setOnClickListener(enabled ? checkboxClickListener : null);
        p1o.setOnClickListener(enabled ? checkboxClickListener : null);
        p2x.setOnClickListener(enabled ? checkboxClickListener : null);
        p2o.setOnClickListener(enabled ? checkboxClickListener : null);
        singleplayer.setOnClickListener(enabled ? checkboxClickListener : null);
        twoplayer.setOnClickListener(enabled ? checkboxClickListener : null);
    }

    @Override
    public void routeToGame() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

}