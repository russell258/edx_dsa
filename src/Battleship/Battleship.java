package Battleship;

import java.util.Scanner;

public class Battleship {
	public static void main(String[] args) {
		/**
		 * 
		 */
		// Initialize 5x5 boards
		final int BOARD_SIZE = 5;
		char[][] player1LocationBoard = new char[BOARD_SIZE][BOARD_SIZE];
		char[][] player2LocationBoard = new char[BOARD_SIZE][BOARD_SIZE];
		char[][] player1TargetHistory = new char[BOARD_SIZE][BOARD_SIZE];
		char[][] player2TargetHistory = new char[BOARD_SIZE][BOARD_SIZE];

		// Initialize all boards to empty ('-')
		initializeBoard(player1LocationBoard);
		initializeBoard(player2LocationBoard);
		initializeBoard(player1TargetHistory);
		initializeBoard(player2TargetHistory);

		Scanner scanner = new Scanner(System.in);

		// Step 1: Print Welcome
		System.out.println("Welcome to Battleship!");
		System.out.println(); // Add a blank line for formatting

		// Step 2 & 3: Player 1 Setup
		setupPlayer(1, player1LocationBoard, scanner);
		printBattleShip(player1LocationBoard);

		// Print 100 new lines
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}

		// Step 2 & 3: Player 2 Setup
		setupPlayer(2, player2LocationBoard, scanner);
		printBattleShip(player2LocationBoard);

		// Print 100 new lines
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}

		// Step 5, 6, 7, 8: Game Loop
		int player1Hits = 0; // Hits Player 1 has scored against Player 2
		int player2Hits = 0; // Hits Player 2 has scored against Player 1
		boolean gameOver = false;
		int currentPlayer = 1;

		do {
			if (currentPlayer == 1) {
				// Player 1's turn
				int hitResult = playerTurn(1, 2, player1TargetHistory, player2LocationBoard, scanner);
				player1Hits += hitResult;

				if (player1Hits == 5) {
					// Player 1 wins
					System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
					gameOver = true;
				} else {
					// Switch to Player 2
					currentPlayer = 2;
				}
			} else {
				// Player 2's turn
				int hitResult = playerTurn(2, 1, player2TargetHistory, player1LocationBoard, scanner);
				player2Hits += hitResult;

				if (player2Hits == 5) {
					// Player 2 wins
					System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
					gameOver = true;
				} else {
					// Switch to Player 1
					currentPlayer = 1;
				}
			}
			// Add a blank line after each turn for spacing
			if (!gameOver) {
				System.out.println();
			}

		} while (!gameOver);

		// Step 8: Print final boards
		System.out.println(); // Add a blank line for formatting
		System.out.println("Final boards:");
		System.out.println(); // Add a blank line for formatting

		printBattleShip(player1LocationBoard);
		System.out.println(); // Add a blank line between boards
		printBattleShip(player2LocationBoard);

		scanner.close();
	}

	/**
	 * Helper method to initialize a board with '-' characters.
	 * @param board The 2D char array to initialize.
	 */
	private static void initializeBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}
	}

	/**
	 * Handles the setup phase for a single player, asking for 5 ship coordinates.
	 * @param playerNum The player number (1 or 2).
	 * @param locationBoard The player's 5x5 location board.
	 * @param scanner The Scanner object for user input.
	 */
	private static void setupPlayer(int playerNum, char[][] locationBoard, Scanner scanner) {
		System.out.println("PLAYER " + playerNum + ", ENTER YOUR SHIPS' COORDINATES.");
		// Use a for loop to get 5 ships
		for (int i = 1; i <= 5; i++) {
			boolean validCoord = false;
			// Use a do-while loop for input validation
			do {
				System.out.println("Enter ship " + i + " location:");
				int row = scanner.nextInt();
				int col = scanner.nextInt();

				// Check for invalid coordinates (out of bounds 0-4)
				if (row < 0 || row >= 5 || col < 0 || col >= 5) {
					System.out.println("Invalid coordinates. Choose different coordinates.");
				}
				// Check if a ship is already at this location
				else if (locationBoard[row][col] == '@') {
					System.out.println("You already have a ship there. Choose different coordinates.");
				}
				// Valid coordinate
				else {
					locationBoard[row][col] = '@';
					validCoord = true;
				}
			} while (!validCoord);
		}
		// System.out.println(); // Add a blank line after setup
	}

	/**
	 * Handles a single player's turn to fire.
	 * @param attacker Player number who is firing.
	 * @param defender Player number who is being fired upon.
	 * @param targetHistory The attacker's target history board.
	 * @param defenderLocationBoard The defender's location board.
	 * @param scanner The Scanner object for user input.
	 * @return 1 if the shot was a hit, 0 if it was a miss.
	 */
	private static int playerTurn(int attacker, int defender, char[][] targetHistory, char[][] defenderLocationBoard, Scanner scanner) {
		boolean validShot = false;
		int hit = 0; // 0 for miss, 1 for hit

		// Use a do-while loop for input validation
		do {
			System.out.println("Player " + attacker + ", enter hit row/column:");
			int row = scanner.nextInt();
			int col = scanner.nextInt();

			// Check for invalid coordinates (out of bounds 0-4)
			if (row < 0 || row >= 5 || col < 0 || col >= 5) {
				System.out.println("Invalid coordinates. Choose different coordinates.");
			}
			// Check if this spot was already fired upon
			else if (targetHistory[row][col] != '-') {
				System.out.println("You already fired on this spot. Choose different coordinates.");
			}
			// Valid shot
			else {
				validShot = true;
				// Check the defender's board
				if (defenderLocationBoard[row][col] == '@') {
					// HIT!
					defenderLocationBoard[row][col] = 'X'; // Update defender's location board
					targetHistory[row][col] = 'X';         // Update attacker's target board
					hit = 1;
					System.out.println("PLAYER " + attacker + " HIT PLAYER " + defender + "'s SHIP!");
				} else {
					// MISS! (Could be '-' or 'O' if already missed, but validation prevents 'O')
					if (defenderLocationBoard[row][col] == '-') {
						defenderLocationBoard[row][col] = 'O'; // Update defender's location board
					}
					targetHistory[row][col] = 'O';         // Update attacker's target board
					hit = 0;
					System.out.println("PLAYER " + attacker + " MISSED!");
				}
			}
		} while (!validShot);

		// Step 4: Print the Target History board after each turn
		printBattleShip(targetHistory);
		return hit;
	}

	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}