package jetray.tictactoe.model.game;

import jetray.tictactoe.model.Player;
import jetray.tictactoe.model.login.LoginData;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class GameInteractor {

    public Player nextPlayer(LoginData loginData, Player stepOwner) {
        if (stepOwner.sign == loginData.playerOne.sign) {
            return loginData.playerTwo;
        } else {
            return loginData.playerOne;
        }
    }

    public int[][] makeStep(int[][] table, Player player, Step step) {
        table[step.x][step.y] = player.sign.ordinal();
        return table;
    }

    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    public boolean checkForWin(int[][] board) {
        return (checkRowsForWin(board) || checkColumnsForWin(board) || checkDiagonalsForWin(board));
    }


    // Loop through rows and see if any are winners.
    private boolean checkRowsForWin(int[][] board) {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }


    // Loop through columns and see if any are winners.
    private boolean checkColumnsForWin(int[][] board) {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }


    // Check the two diagonals to see if either is a win. Return true if either wins.
    private boolean checkDiagonalsForWin(int[][] board) {
        return ((checkRowCol(
            board[0][0], board[1][1], board[2][2])
        ) || (checkRowCol(board[0][2], board[1][1], board[2][0])));
    }

    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(int c1, int c2, int c3) {
        return ((c1 != -1) && (c1 == c2) && (c2 == c3));
    }


}
