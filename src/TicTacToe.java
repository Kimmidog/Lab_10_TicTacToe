import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain = true;
        String player = "X";
        int moveCount;

        while (playAgain) {
            clearBoard();
            moveCount = 0;
            while (true) {
                display();
                int row = SafeInput.getRangedInt(console, "Enter row (1-3) for player " + player + ": ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(console, "Enter column (1-3) for player " + player + ": ", 1, 3) - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = player;
                    moveCount++;
                } else {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }

                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println(player + " wins!");
                    break;
                } else if (moveCount == 9 && isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    break;
                }

                player = player.equals("X") ? "O" : "X";
            }

            playAgain = SafeInput.getYNConfirm(console, "Do you want to play again (y/n)? ");
        }
    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("  1 2 3");
        for (int row = 0; row < ROWS; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col]);
                if (col < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("  -----");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
