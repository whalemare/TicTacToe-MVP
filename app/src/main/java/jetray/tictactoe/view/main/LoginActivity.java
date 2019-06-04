package jetray.tictactoe.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jetray.tictactoe.Afterstart;
import jetray.tictactoe.R;
import jetray.tictactoe.controller.LoginController;
import jetray.tictactoe.model.Difficult;

public class LoginActivity extends AppCompatActivity implements LoginView {

    public EditText playerOne;
    public EditText playerTwo;

    public Spinner difficulty;
    public CharSequence player1 = "Игрок 1";
    public CharSequence player2 = "Игрок 2";

    public CharSequence cloneplayer2;
    boolean player1ax = true;
    boolean selectedSinglePlayer;
    public CheckBox p1x, p1o, p2x, p2o, singleplayer, twoplayer;

    private LoginController controller = new LoginController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller.onAttach(this);

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

        p1x.setOnClickListener(checkboxClickListener);
        p1o.setOnClickListener(checkboxClickListener);
        p2x.setOnClickListener(checkboxClickListener);
        p2o.setOnClickListener(checkboxClickListener);
        singleplayer.setOnClickListener(checkboxClickListener);
        twoplayer.setOnClickListener(checkboxClickListener);

        difficulty.setEnabled(false);

        p1x.setChecked(true);
        p2o.setChecked(true);
        twoplayer.setChecked(true);

        playerOne.addTextChangedListener(new TextWatcher() {                               /*this code take player1's name characterwise i.e it takes one character at a time and
                                                                                         saved to string variable player1*/
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player1 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        playerTwo.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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


    View.OnClickListener checkboxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox) view).isChecked();
            if (checked) {
                switch (view.getId()) {
                    case R.id.player1x:
                        p1o.setChecked(false);
                        p2x.setChecked(false);
                        p2o.setChecked(true);
                        player1ax = true;
                        break;
                    case R.id.player1o:
                        p1x.setChecked(false);
                        p2o.setChecked(false);
                        p2x.setChecked(true);
                        player1ax = false;
                        break;
                    case R.id.player2x:
                        p2o.setChecked(false);
                        p1x.setChecked(false);
                        p1o.setChecked(true);
                        player1ax = false;
                        break;
                    case R.id.player2o:
                        p2x.setChecked(false);
                        p1o.setChecked(false);
                        p1x.setChecked(true);
                        player1ax = true;
                        break;
                    case R.id.splayer:
                        twoplayer.setChecked(false);
                        selectedSinglePlayer = true;
                        cloneplayer2 = player2;
                        playerTwo.setText("CPU");

                        playerOne.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        playerOne.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE);

                        difficulty.setEnabled(true);
                        break;
                    case R.id.tplayer:
                        singleplayer.setChecked(false);
                        selectedSinglePlayer = false;
                        playerTwo.setText(cloneplayer2);
                        playerOne.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        playerOne.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_NEXT);
                        difficulty.setEnabled(false);
                        break;
                }

            } else {
                switch (view.getId()) {
                    case R.id.player1x:
                        p1o.setChecked(true);
                        p2x.setChecked(true);
                        p2o.setChecked(false);
                        player1ax = false;
                        break;
                    case R.id.player1o:
                        p1x.setChecked(true);
                        p2o.setChecked(true);
                        p2x.setChecked(false);
                        player1ax = true;
                        break;
                    case R.id.player2x:
                        p2o.setChecked(true);
                        p1x.setChecked(true);
                        p1o.setChecked(false);
                        player1ax = true;
                        break;
                    case R.id.player2o:
                        p2x.setChecked(true);
                        p1o.setChecked(true);
                        p1x.setChecked(false);
                        player1ax = false;
                        break;
                    case R.id.splayer:
                        twoplayer.setChecked(true);
                        selectedSinglePlayer = false;
                        playerTwo.setText(cloneplayer2);
                        difficulty.setEnabled(false);
                        playerOne.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        playerOne.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_NEXT);
                        break;
                    case R.id.tplayer:
                        singleplayer.setChecked(true);
                        selectedSinglePlayer = true;
                        playerTwo.setText("CPU");
                        playerOne.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        playerOne.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE);
                        difficulty.setEnabled(true);
                        break;
                }

            }

        }
    };


    public void onClickStartGame(View view) {
        if (!selectedSinglePlayer)
            if (player2.length() == 0)
                player2 = "Игрок 2";
        if (player1.length() == 0)
            player1 = "Игрок 1";

        CharSequence[] players = {player1, player2};
        Intent i = new Intent(this, Afterstart.class);
        i.putExtra("difficult", "easy"); // todo: refactor
        i.putExtra("playersnames", players);
        i.putExtra("player1ax", player1ax);
        i.putExtra("selectedsingleplayer", selectedSinglePlayer);
        startActivity(i);
    }

}