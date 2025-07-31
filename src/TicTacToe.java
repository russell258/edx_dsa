import java.util.Scanner;

public class TicTacToe {
    
    static char[][] board = new char[3][3];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        initializeBoard();

        char currentPlayer = 'X';
        boolean gameOver = false;

        while (!gameOver){
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column with 0 - 2): ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (row<0||row>=3||col<0||col>=3||board[row][col]!='-'){
                System.out.println("This move is not valid. Try again.");
                continue;
            }

            board[row][col] = currentPlayer;
            
            if (checkWin(currentPlayer)){
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (isBoardFull()){
                printBoard();
                System.out.println("The game is a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
            }
        }
        sc.close();
    }

    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0 ; j <3 ; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard() {
        System.out.println("Current Board: " );
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); // new line after each row
        }
    }

    public static boolean checkWin(char player) {
        for (int i=0;i<3;i++){
            if (board[i][0]==player && board[i][1]==player && board[i][2]==player) return true; // check rows
            if (board[0][i]==player && board[1][i]==player && board[2][i]==player) return true; // check columns
        }

        if (board[0][0]==player && board[1][1]==player && board[2][2]==player) return true; // check main diagonal
        if (board[0][2]==player && board[1][1]==player && board[2][0]==player) return true; // check anti diagonal
        
        return false;
    }

    public static boolean isBoardFull() {
        for (int i =0; i <3 ; i++){
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; //if any cell is empty, board is not full
                }
            }
        }
        return true; //if all cells are filled, board is full
    }

}
