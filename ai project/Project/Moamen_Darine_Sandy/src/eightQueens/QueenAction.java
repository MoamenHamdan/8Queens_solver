package eightQueens;

public class QueenAction implements Action {
    private int row;
    private int col;

    public QueenAction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int[][] apply(int[][] state) {
        int[][] newState = new int[state.length][];
        for (int i = 0; i < state.length; i++) {
            newState[i] = state[i].clone();
        }
        newState[row][col] = 1;

        return newState;
    }

    @Override
    public int getCostPerAction() {
        return 1;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
