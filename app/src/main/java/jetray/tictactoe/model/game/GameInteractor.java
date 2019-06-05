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
        int[][] cloneTable = newTable();
        for (int row = 0; row < cloneTable.length; row++) {
            for (int column = 0; column < table.length; column++) {
                cloneTable[row][column] = table[row][column];
            }
        }

        if (cloneTable[step.x][step.y] == -1) {
            cloneTable[step.x][step.y] = player.sign.ordinal();
        }
        return cloneTable;
    }

    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    public boolean isGameFinished(int[][] board) {
        if (isDraw(board)) return true;

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

    public GameState checkGameResult(int[][] table, Player stepOwner) {
        if (isDraw(table)) {
            return GameState.DRAW;
        } else {
            stepOwner.countWin++;
            GameState.WIN.player = stepOwner; // sry for this
            return GameState.WIN;
        }
    }

    private boolean isDraw(int[][] table) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (table[row][col] == -1) {
                    return false;  // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no empty cell, it's a draw
    }

    public int[][] newTable() {
        return new int[][]{
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}
        };
    }

    public boolean isNeedNextPlayer(int[][] table, Step step) {
        if (table[step.x][step.y] != -1) {
            return false;
        } else {
            return true;
        }
    }
}
