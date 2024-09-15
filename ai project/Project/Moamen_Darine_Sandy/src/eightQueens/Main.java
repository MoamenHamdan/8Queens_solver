package eightQueens;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Select the initial configuration for the Eight Queens puzzle:");
		System.out.print("Enter your choice (1 or 2): ");
		int choice = scanner.nextInt();

		int[][] initialState = null;
		int goal[][] = new int[][] { { 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0 } };
		switch (choice) {
		case 1:
			initialState = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };
			break;
		case 2:
			initialState = new int[][]{ { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0 } };
			break;
		default:
			System.out.println("Invalid choice. Please select a number between 1 and 2.");
			scanner.close();
			return;
		}

		EightQueensBoard board = new EightQueensBoard(initialState, goal);
		printBoard(board.getInitialState());
		while (true) {
			System.out.println("Select the solver you want to use:");
			System.out.println("1: BFS Solver");
			System.out.println("2: DFS Solver");
			System.out.println("3: UCS Solver");
			System.out.println("4: A* Solver");
			System.out.println("5: Exit");
			System.out.print("Enter your choice (1-5): ");
			int solverChoice = scanner.nextInt();

			switch (solverChoice) {
			case 1:
				BfsSolver bfs = new BfsSolver();
				bfs.solve(board);
				break;
			case 2:
				DfsSolver dfs = new DfsSolver();
				dfs.solve(board);
				break;
			case 3:
				UcsSolver ucs = new UcsSolver();
				ucs.solve(board);
				break;
			case 4:
				AStarSolver aStar = new AStarSolver();
				aStar.solve(board);
				break;
			case 5:
				System.out.println("Exiting program.");
				scanner.close();
				return;
			default:
				System.out.println("Invalid choice, please select a number between 1 and 5.");
				break;
			}
			System.out.println();
		}
	}

	public static void printBoard(int[][] board) {
	    System.out.println("+---+---+---+---+---+---+---+---+");
	    for (int i = 0; i < board.length; i++) {
	        System.out.print("| ");
	        for (int j = 0; j < board[i].length; j++) {
	            // Print 'Q' for Queen and '.' for empty space
	            System.out.print(board[i][j] == 1 ? "Q " : ". ");
	            System.out.print("| ");
	        }
	        System.out.println();
	        System.out.println("+---+---+---+---+---+---+---+---+");
	    }
	}

}