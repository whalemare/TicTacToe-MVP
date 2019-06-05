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

import java.util.Random;

import jetray.tictactoe.controller.game.GameController;
import jetray.tictactoe.model.game.GameData;
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
    TextView playerNameTwo;
    GameController controller = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_afterstart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playerNameOne = findViewById(R.id.playerone);
        playerNameTwo = findViewById(R.id.playertwo);
        cell_00 = findViewById(R.id.table_00);
        cell_01 = findViewById(R.id.table_01);
        cell_02 = findViewById(R.id.table_02);
        cell_10 = findViewById(R.id.table_10);
        cell_11 = findViewById(R.id.table_11);
        cell_12 = findViewById(R.id.table_12);
        cell_20 = findViewById(R.id.table_20);
        cell_21 = findViewById(R.id.table_21);
        cell_22 = findViewById(R.id.table_22);

        controller.onAttach(this);
    }

    @Override
    public void render(LoginData loginData, GameData gameData) {
        playerNameOne.setText(loginData.playerOne.name);
        playerNameTwo.setText(loginData.playerTwo.name);

        renderTable(gameData.table);
    }

    private void renderTable(int[][] table) {
        if (table[0][0] == 1) cell_00.setImageResource(R.drawable.x);
        if (table[0][0] == 0) cell_00.setImageResource(R.drawable.oo);


        if (table[0][1] == 1) cell_01.setImageResource(R.drawable.x);
        if (table[0][1] == 0) cell_01.setImageResource(R.drawable.oo);


        if (table[0][2] == 1) cell_02.setImageResource(R.drawable.x);
        if (table[0][2] == 0) cell_02.setImageResource(R.drawable.oo);


        if (table[1][0] == 1) cell_10.setImageResource(R.drawable.x);
        if (table[1][0] == 0) cell_10.setImageResource(R.drawable.oo);

        if (table[1][1] == 1) cell_11.setImageResource(R.drawable.x);
        if (table[1][1] == 0) cell_11.setImageResource(R.drawable.oo);


        if (table[1][2] == 1) cell_12.setImageResource(R.drawable.x);
        if (table[1][2] == 0) cell_12.setImageResource(R.drawable.oo);

        if (table[2][0] == 1) cell_20.setImageResource(R.drawable.x);
        if (table[2][0] == 0) cell_20.setImageResource(R.drawable.oo);


        if (table[2][1] == 1) cell_21.setImageResource(R.drawable.x);
        if (table[2][1] == 0) cell_21.setImageResource(R.drawable.oo);

        if (table[2][2] == 1) cell_22.setImageResource(R.drawable.x);
        if (table[2][2] == 0) cell_22.setImageResource(R.drawable.oo);
    }

    public void kzz(View view) {
        if (win == 0 && buttonpressed[0][0] == 0) {
            if (flag % 2 == 0)
                tracker[0][0] = ax;
            else
                tracker[0][0] = zero;

//            renderTable();
            winchecker();
            cpuplay();
            flag++;
            buttonpressed[0][0]++;
        }
    }


    public void kzo(View view) {

        if (win == 0 && buttonpressed[0][1] == 0) {
            if (flag % 2 == 0) tracker[0][1] = ax;
            else tracker[0][1] = zero;

//            renderTable();
            winchecker();
            cpuplay();
            buttonpressed[0][1]++;
            flag++;
        }
    }

    public void kzt(View view) {
        if (win == 0 && buttonpressed[0][2] == 0) {
            if (flag % 2 == 0) tracker[0][2] = ax;
            else tracker[0][2] = zero;

//            renderTable();
            winchecker();
            cpuplay();
            buttonpressed[0][2]++;
            flag++;
        }
    }

    public void koz(View v) {
        if (win == 0 && buttonpressed[1][0] == 0) {
            if (flag % 2 == 0) tracker[1][0] = ax;
            else tracker[1][0] = zero;

//            renderTable();
            winchecker();
            cpuplay();

            ++buttonpressed[1][0];
            flag++;
        }
    }

    public void koo(View v) {
        if (win == 0 && buttonpressed[1][1] == 0) {
            if (flag % 2 == 0) tracker[1][1] = ax;
            else tracker[1][1] = zero;
//            renderTable();
            winchecker();
            cpuplay();
            ++buttonpressed[1][1];
            flag++;
        }
    }

    public void kot(View v) {
        if (win == 0 && buttonpressed[1][2] == 0) {
            if (flag % 2 == 0) tracker[1][2] = ax;
            else tracker[1][2] = zero;

//            renderTable();
            winchecker();
            cpuplay();
            ++buttonpressed[1][2];
            flag++;
        }
    }

    public void ktz(View v) {
        if (win == 0 && buttonpressed[2][0] == 0) {
            if (flag % 2 == 0) tracker[2][0] = ax;
            else tracker[2][0] = zero;

////            renderTable();
            winchecker();
            cpuplay();
            ++buttonpressed[2][0];
            flag++;
        }
    }

    public void kto(View v) {
        if (win == 0 && buttonpressed[2][1] == 0) {
            if (flag % 2 == 0) tracker[2][1] = ax;
            else tracker[2][1] = zero;
////            renderTable();
            winchecker();
            cpuplay();
            ++buttonpressed[2][1];
            flag++;
        }
    }

    public void ktt(View v) {
        if (win == 0 && buttonpressed[2][2] == 0) {
            if (flag % 2 == 0) tracker[2][2] = ax;
            else tracker[2][2] = zero;

////            renderTable();
            winchecker();
            cpuplay();
            ++buttonpressed[2][2];
            flag++;
        }
    }

    public void cpuplay() {
        if ((singlePlayer) && (win == 0)) {
            if (ifcpuwin()) ;
            else if (ifopowin()) ;
            else if (emptycentre()) ;
            else if (emptycorner()) ;
            else emptyany();


            /***  final Handler handler = new Handler();
             Timer t = new Timer();
             t.schedule(new TimerTask() {
             public void run() {
             handler.post(new Runnable() {
             public void run() {

             //add code to be executed after a pause

             }
             });
             }
             }, 80);****/
//            renderTable();
            winchecker();

            flag++;
            return;
        }
    }

    public boolean ifcpuwin() {
//        if (!easy) {
//            for (i = 0; i < 8; i++) {
//                if (sum[i] == 2 * zero) {
//                    if (i == 0) {
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[0][x] == 0)
//                                tracker[0][x] = zero;
//                    }
//
//                    if (i == 1) {
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[1][x] == 0)
//                                tracker[1][x] = zero;
//                    }
//                    if (i == 2) {
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[2][x] == 0)
//                                tracker[2][x] = zero;
//                    }
//
//                    if (i == 3) {
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[x][0] == 0)
//                                tracker[x][0] = zero;
//                    }
//
//                    if (i == 4) {
//
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[x][1] == 0)
//                                tracker[x][1] = zero;
//                    }
//
//                    if (i == 5) {
//
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[x][2] == 0)
//                                tracker[x][2] = zero;
//                    }
//                    if (i == 6) {
//
//                        for (int y = 0; y < 3; y++)
//                            for (int x = 0; x < 3; x++)
//                                if (x == y)
//                                    if (tracker[x][y] == 0)
//                                        tracker[x][y] = zero;
//                    }
//                    if (i == 7) {
//                        if (tracker[0][2] == 0)
//                            tracker[0][2] = zero;
//                        else if (tracker[1][1] == 0)
//                            tracker[1][1] = zero;
//                        else tracker[2][0] = zero;
//
//                    }
//                    return true;
//                }
//            }
//        }
        return false;
    }


    public boolean ifopowin() {
//        if ((!easy) || (!medium)) {
//
//            for (i = 0; i < 8; i++) {
//                if (sum[i] == 2 * ax) {
//                    if (i == 0) {
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[0][x] == 0) {
//                                tracker[0][x] = zero;
//                                buttonpressed[0][x]++;
//                            }
//                    }
//
//                    if (i == 1) {
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[1][x] == 0) {
//                                tracker[1][x] = zero;
//                                buttonpressed[1][x]++;
//                            }
//                    }
//                    if (i == 2) {
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[2][x] == 0) {
//                                tracker[2][x] = zero;
//                                buttonpressed[2][x]++;
//                            }
//                    }
//
//                    if (i == 3) {
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[x][0] == 0) {
//                                tracker[x][0] = zero;
//                                buttonpressed[x][0]++;
//                            }
//                    }
//
//                    if (i == 4) {
//
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[x][1] == 0) {
//                                tracker[x][1] = zero;
//                                buttonpressed[x][1]++;
//                            }
//                    }
//
//                    if (i == 5) {
//
//                        for (int x = 0; x < 3; x++)
//                            if (tracker[x][2] == 0) {
//                                tracker[x][2] = zero;
//                                buttonpressed[x][2]++;
//                            }
//                    }
//                    if (i == 6) {
//
//                        for (int y = 0; y < 3; y++)
//                            for (int x = 0; x < 3; x++)
//                                if (x == y)
//                                    if (tracker[x][y] == 0) {
//                                        tracker[x][y] = zero;
//                                        buttonpressed[x][y]++;
//                                    }
//
//
//                    }
//                    if (i == 7) {
//                        if (tracker[0][2] == 0) {
//                            tracker[0][2] = zero;
//                            buttonpressed[0][2]++;
//                        } else if (tracker[1][1] == 0) {
//                            tracker[1][1] = zero;
//                            buttonpressed[1][1]++;
//                        } else {
//                            tracker[2][0] = zero;
//                            buttonpressed[2][0]++;
//                        }
//
//
//                    }
//                    return true;
//                }
//            }
//
//        }
        return false;
    }

    public boolean emptycentre() {
//        if (impossible || hard) {
//            if (tracker[1][1] == 0) {
//                tracker[1][1] = zero;
//                buttonpressed[1][1]++;
//                return true;
//            }
//        }
        return false;
    }

    public boolean emptycorner() {


//        if (hard || impossible)
//            if (((tracker[0][0] + tracker[2][2]) == 2 * ax) || ((tracker[0][2] + tracker[2][0]) == 2 * ax)) {
//                for (int k = 0; k < 3; k++)
//                    for (int j = 0; j < 3; j++)
//                        if ((k + j) % 2 == 1) {
//                            if (tracker[k][j] == 0)
//                                tracker[k][j] = zero;
//                            buttonpressed[k][j]++;
//                            return true;
//                        }
//            }
//
//
//        if (impossible)
//            if (sum[6] == zero || sum[7] == zero) {
//                if (sum[6] == zero) {
//                    if ((sum[0] + sum[3]) > (sum[2] + sum[5])) {
//                        tracker[0][0] = zero;
//                        buttonpressed[0][0]++;
//                    } else {
//                        tracker[2][2] = zero;
//                        buttonpressed[2][2]++;
//                    }
//                    return true;
//                }
//
//                if (sum[7] == zero) {
//                    if ((sum[0] + sum[5]) > (sum[3] + sum[2])) {
//                        tracker[0][2] = zero;
//                        buttonpressed[0][2]++;
//                    } else {
//                        tracker[2][0] = zero;
//                        buttonpressed[2][0]++;
//                    }
//                    return true;
//                }
//
//            }
//
//
//        for (int i = 0; i < 3; i++) {
//            if (tracker[0][i] == ax) {
//                if (tracker[0][0] == 0) {
//                    tracker[0][0] = zero;
//                    buttonpressed[0][0]++;
//                    return true;
//                }
//                if (tracker[0][2] == 0) {
//                    tracker[0][2] = zero;
//                    buttonpressed[0][2]++;
//                    return true;
//                }
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//
//            if (tracker[2][i] == ax) {
//                if (tracker[2][0] == 0) {
//                    tracker[2][0] = zero;
//                    buttonpressed[2][0]++;
//                    return true;
//                }
//                if (tracker[2][2] == 0) {
//                    tracker[2][2] = zero;
//                    buttonpressed[2][2]++;
//                    return true;
//                }
//            }
//        }
//        for (int i = 0; i < 3; i++) {
//            if (tracker[i][0] == ax) {
//                if (tracker[0][0] == 0) {
//                    tracker[0][0] = zero;
//                    buttonpressed[0][0]++;
//                    return true;
//                }
//                if (tracker[2][0] == 0) {
//                    tracker[2][0] = zero;
//                    buttonpressed[2][0]++;
//                    return true;
//                }
//            }
//        }
//        for (int i = 0; i < 3; i++) {
//            if (tracker[i][2] == ax) {
//                if (tracker[0][2] == 0) {
//                    tracker[0][2] = zero;
//                    buttonpressed[0][2]++;
//                    return true;
//                }
//                if (tracker[2][2] == 0) {
//                    tracker[2][2] = zero;
//                    buttonpressed[2][2]++;
//                    return true;
//                }
//            }
//        }
        return false;
    }

    public void emptyany() {

        if (ctrflag == 0)
            while (true) {
                int x = rand();
                int y = rand();

                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;

                }
            }

        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;
                }


    }

    public int rand() {
        return r.nextInt(3);
    }


    public void showDialog(String whoWon, String scoreWon, String whoLose, String scoreLose) {

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
            doreset();
        });

        playAgainButton.setOnClickListener(view -> {
            dialog.dismiss();
            playmore();
        });
    }

    public void winchecker() {
//        ctrflag++;
//        sum[0] = tracker[0][0] + tracker[0][1] + tracker[0][2];
//        sum[1] = tracker[1][0] + tracker[1][1] + tracker[1][2];
//        sum[2] = tracker[2][0] + tracker[2][1] + tracker[2][2];
//        sum[3] = tracker[0][0] + tracker[1][0] + tracker[2][0];
//        sum[4] = tracker[0][1] + tracker[1][1] + tracker[2][1];
//        sum[5] = tracker[0][2] + tracker[1][2] + tracker[2][2];
//        sum[6] = tracker[0][0] + tracker[1][1] + tracker[2][2];
//        sum[7] = tracker[0][2] + tracker[1][1] + tracker[2][0];
//
//
//        currentgamedonechecker++;
//        resetchecker++;
//
//        for (int i = 0; i < 8; i++)
//            if (sum[i] == 3 || sum[i] == 30) {
//                win++;
//                if ((sum[i] == 3) && (ax == 1)) {
//                    score1++;
//                    TextView q1 = (TextView) findViewById(R.id.p1score);
//                    q1.setText("" + score1);
//                    showDialog("" + player1 + " won!", "" + score1, "" + player2, "" + score2);
//
//                }
//                if ((sum[i] == 3) && (zero == 1)) {
//                    score2++;
//                    TextView q1 = (TextView) findViewById(R.id.p2score);
//                    q1.setText("" + score2);
//                    showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);
//
//                }
//                if ((sum[i] == 30) && (ax == 10)) {
//                    score1++;
//                    TextView q1 = (TextView) findViewById(R.id.p1score);
//                    q1.setText("" + score1);
//                    showDialog("" + player1 + " won!", "" + score1, "" + player2, "" + score2);
//
//                }
//                if ((sum[i] == 30) && (zero == 10)) {
//                    score2++;
//                    TextView q1 = (TextView) findViewById(R.id.p2score);
//                    q1.setText("" + score2);
//                    showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);
//
//                }
//
//            }
//
//        if ((ctrflag == 9) && (win == 0)) {
//            showDialog("This is a draw !", "" + score1, "" + player2, "" + score2);
//
//            drawchecker++;
//        }


    }  //end winchecker()

    private void playmore() {
//        if ((drawchecker > 0) || (win > 0)) {
//            game++;
//            TextView qq = (TextView) findViewById(R.id.gameNumber);
//            qq.setText("" + game);
//
//            for (int i = 0; i < 8; i++)
//                sum[i] = 0;
//
//            drawchecker = 0;
//
//
//            ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;
//            q1 = findViewById(R.id.table_00);
//            q2 = findViewById(R.id.table_01);
//            q3 = findViewById(R.id.table_02);
//            q4 = findViewById(R.id.table_10);
//            q5 = findViewById(R.id.table_11);
//            q6 = findViewById(R.id.table_12);
//            q7 = findViewById(R.id.table_20);
//            q8 = findViewById(R.id.table_21);
//            q9 = findViewById(R.id.table_22);
//            q1.setImageDrawable(null);
//            q2.setImageDrawable(null);
//            q3.setImageDrawable(null);
//            q4.setImageDrawable(null);
//            q5.setImageDrawable(null);
//            q6.setImageDrawable(null);
//            q7.setImageDrawable(null);
//            q8.setImageDrawable(null);
//            q9.setImageDrawable(null);
//
//            for (int i = 0; i < 3; i++)
//                for (int j = 0; j < 3; j++)
//                    buttonpressed[i][j] = 0;
//
//            for (int i = 0; i < 3; i++)
//                for (int j = 0; j < 3; j++)
//                    tracker[i][j] = 0;
//
//
//            if ((game + 1) % 2 == 0)
//                Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this, "" + player2 + "\'s turn", Toast.LENGTH_SHORT).show();
//            win = 0;
//            summ = 0;
//            ctrflag = 0;
//            flag = (game + 1) % 2;
//            currentgamedonechecker = 0;
//
//            if (singlePlayer && (game % 2 == 0))
//                cpuplay();
//        }
    }


    public void doreset() {
//        TextView qq = (TextView) findViewById(R.id.gameNumber);
//        qq.setText("" + 1);
//
//
//        for (int i = 0; i < 3; i++)
//            for (int j = 0; j < 3; j++)
//                tracker[i][j] = 0;
//
//        for (int i = 0; i < 3; i++)
//            for (int j = 0; j < 3; j++)
//                buttonpressed[i][j] = 0;
//
//        ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;
//
//        q1 = findViewById(R.id.table_00);
//        q2 = findViewById(R.id.table_01);
//        q3 = findViewById(R.id.table_02);
//        q4 = findViewById(R.id.table_10);
//        q5 = findViewById(R.id.table_11);
//        q6 = findViewById(R.id.table_12);
//        q7 = findViewById(R.id.table_20);
//        q8 = findViewById(R.id.table_21);
//        q9 = findViewById(R.id.table_22);
//        q1.setImageDrawable(null);
//        q2.setImageDrawable(null);
//        q3.setImageDrawable(null);
//        q4.setImageDrawable(null);
//        q5.setImageDrawable(null);
//        q6.setImageDrawable(null);
//        q7.setImageDrawable(null);
//        q8.setImageDrawable(null);
//        q9.setImageDrawable(null);
//
//
//        win = 0;
//        drawchecker = 0;
//        summ = 0;
//        resetchecker = 0;
//        ctrflag = 0;
//        score1 = 0;
//        score2 = 0;
//        game = 1;
//        flag = 0;
//        currentgamedonechecker = 0;
//        TextView qqq = (TextView) findViewById(R.id.p1score);
//        qqq.setText("" + score1);
//        TextView qqqq = (TextView) findViewById(R.id.p2score);
//        qqqq.setText("" + score2);
//
//        Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();
    }

    private void showExitDialog() {
        final Dialog dialog = new Dialog(GameActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout_exit);
        dialog.setCancelable(false);

        dialog.show();

        Button exit = dialog.findViewById(R.id.yes_button);
        final Button dismiss = dialog.findViewById(R.id.no_button);

        exit.setOnClickListener(view -> {
            doreset();
            finish();
        });

        dismiss.setOnClickListener(view -> dialog.dismiss());
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

}


