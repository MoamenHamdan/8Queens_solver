package eightQueens;

import java.util.Arrays;

public class EightQueensBoard {
	static final int GRID_SIZE = 8;
	int hamming = 0;
	int manhattan = 0;
	private int[][] initialState;
	private int[][] goal;

	public EightQueensBoard(int[][] S, int[][] G) {
		initialState = S;
		goal = G;
	}

	

	public int calcHammingDistance(int[][] currentState) {
		int hamming = 0;
		for (int i = 0; i < currentState.length; i++) {
			for (int j = 0; j < currentState[i].length; j++) {
				if (currentState[i][j] != goal[i][j]) {

					hamming++;
				}
			}
		}
		return hamming;
	}

	public int calcManhattanDistance(int[][] currentState) {
		int manhattan = 0;
		int n = currentState.length;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				int value = currentState[x][y];
				if (value != 0) {
					int targetX = (value - 1) / n; // Correct row for the value
					int targetY = (value - 1) % n; // Correct column for the value
					manhattan += Math.abs(x - targetX) + Math.abs(y - targetY);
				}
			}
		}
		return manhattan;
	}

	public int[][] getInitialState() {
		return initialState;
	}

	public int[][] getGoal() {
		return goal;
	}

	public boolean isGoalState(int[][] intState) {
		return (Arrays.deepEquals(intState, goal));
	}

	public boolean equalState(int[][] state1, int[][] state2) {
		return (Arrays.deepEquals(state1, state2));
	}

	public int getCostPerAction() {
		return 1;
	}

	public SuccessorFunction getSuccessorFunction() {
		return new EightQueensSuccessorFunction();
	}

}
