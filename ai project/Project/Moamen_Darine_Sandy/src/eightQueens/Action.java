package eightQueens;

public interface Action {
	int[][] apply(int[][] state);

	int getCostPerAction();

}
