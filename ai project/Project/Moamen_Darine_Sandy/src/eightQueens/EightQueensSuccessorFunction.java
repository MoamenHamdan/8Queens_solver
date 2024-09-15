package eightQueens;

import java.util.ArrayList;

public class EightQueensSuccessorFunction implements SuccessorFunction {

	@Override
	public ArrayList<Action> getPossibleApplicableActions(int[][] state) {
		ArrayList<Action> applicableActions = new ArrayList<>();

		int row = findEmptyRow(state);
		if (row != -1) {
			for (int col = 0; col < state[row].length; col++) {
				if (isSafe(state, row, col)) {
					applicableActions.add(new QueenAction(row, col));
				}
			}
		}
		return applicableActions;
	}

	private int findEmptyRow(int[][] state) {
		for (int row = 0; row < state.length; row++) {
			boolean empty = true;
			for (int col = 0; col < state[row].length; col++) {
				if (state[row][col] == 1) {
					empty = false;
					break;
				}
			}
			if (empty)
				return row;
		}
		return -1;
	}

	public boolean isSafe(int[][] board, int row, int col) {
		for (int x = 0; x < col; x++)						  //column
			if (board[row][x] == 1)
				return false;
		for (int x = row, y = col; x >= 0 && y >= 0; x--, y--)//diagonal
			if (board[x][y] == 1)
				return false;
		for (int x = row, y = col; x < 8 && y >= 0; x++, y--)//diagonal 2
			if (board[x][y] == 1)
				return false;
		return true;
	}
}